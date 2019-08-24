package com.example.markerimpl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.markerimpl.cluster.SegmentClusterItem
import com.example.markerimpl.cluster.SegmentClusterRenderer
import com.example.markerimpl.cluster.SegmentInfoWindowAdapter
import com.example.markerimpl.data.Segment
import com.example.markerimpl.data.Station
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.clustering.ClusterManager

class ClusteringActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map.apply {
            moveCamera(CameraUpdateFactory.newLatLngZoom(Station.Osaka.position, 12.0f))

            uiSettings.run {
                isRotateGesturesEnabled = false
                isTiltGesturesEnabled = false
            }

            ClusterManager<SegmentClusterItem>(this@ClusteringActivity, this).let {
                it.renderer = SegmentClusterRenderer(this@ClusteringActivity, this, it)
                setOnCameraIdleListener(it)
                setOnMarkerClickListener(it)

                Segment.values().forEach { segment ->
                    it.addItem(SegmentClusterItem(segment))
                }
            }

            setInfoWindowAdapter(SegmentInfoWindowAdapter(this@ClusteringActivity))
        }
    }
}