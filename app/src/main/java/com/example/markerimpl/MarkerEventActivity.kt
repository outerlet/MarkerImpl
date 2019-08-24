package com.example.markerimpl

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.markerimpl.cluster.StationInfoWindowAdapter
import com.example.markerimpl.data.Station
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_common.*

class MarkerEventActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map.apply {
            moveCamera(CameraUpdateFactory.newLatLngZoom(Station.Osaka.position, 16.0f))

            Station.values().forEach { station ->
                addMarker(MarkerOptions()
                    .title(station.title)
                    .snippet(station.snippet)
                    .position(station.position)
                    .draggable(true)
                    .zIndex(2.0f)
                ).tag = station
            }

            setOnMarkerClickListener { marker ->
                Snackbar
                    .make(container, (marker.tag as Station).address, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.message_focus) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(marker.position), 500, null)
                    }
                    .show()

                marker.showInfoWindow()

                true
            }

            setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
                private var start: LatLng? = null
                private var end: LatLng? = null

                override fun onMarkerDragStart(marker: Marker) {
                    marker.position.let {
                        start = LatLng(it.latitude, it.longitude)
                    }
                }

                override fun onMarkerDrag(marker: Marker) {
                    // Do Nothing.
                }

                override fun onMarkerDragEnd(marker: Marker) {
                    marker.position.let {
                        end = LatLng(it.latitude, it.longitude)

                        if (start != null && end != null) {
                            val message = String.format("(%.4f, %.4f) -> (%.4f, %.4f)", start!!.latitude, start!!.longitude, end!!.latitude, end!!.longitude)
                            Snackbar
                                .make(container, message, Snackbar.LENGTH_LONG)
                                .setAction(R.string.message_focus_2) {
                                    mMap.animateCamera(CameraUpdateFactory.newLatLng(marker.position), 500, null)
                                }
                                .show()
                        }

                        start = null
                        end = null
                    }
                }
            })

            setOnInfoWindowClickListener { marker ->
                Toast.makeText(this@MarkerEventActivity, "${marker.title}のInfoWindowがクリックされました", Toast.LENGTH_LONG).show()
            }

            setInfoWindowAdapter(StationInfoWindowAdapter(this@MarkerEventActivity))
        }
    }
}
