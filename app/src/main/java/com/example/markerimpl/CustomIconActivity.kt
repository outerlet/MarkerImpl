package com.example.markerimpl

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.markerimpl.data.Station
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ui.IconGenerator

class CustomIconActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        val fragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap.apply {
            moveCamera(CameraUpdateFactory.newLatLngZoom(Station.Osaka.position, 15.0f))

            val textView: TextView
            val iconGenerator = IconGenerator(this@CustomIconActivity).apply {
                setBackground(null)
                textView = layoutInflater.inflate(R.layout.icon_station_name, null, false) as TextView
                setContentView(textView)
            }

            Station.values().forEach { station ->
                addMarker(MarkerOptions()
                    .position(station.position)
                    .zIndex(2.0f))

                textView.text = station.title

                addMarker(MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon()))
                    .position(station.position)
                    .anchor(0.5f, 0.0f)
                    .zIndex(2.1f))
            }

            setOnMarkerClickListener { marker ->
                animateCamera(CameraUpdateFactory.newLatLng(marker.position), 300, null)
                true
            }
        }
    }
}
