package com.example.markerimpl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.markerimpl.cluster.MoreSegmentClusterRenderer
import com.example.markerimpl.cluster.SegmentClusterItem
import com.example.markerimpl.cluster.SegmentInfoWindowAdapter
import com.example.markerimpl.data.Segment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.clustering.ClusterManager

class MoreClusteringActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        val fragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap.apply {
            moveCamera(CameraUpdateFactory.newLatLngZoom(Segment.Kita.coordinate, 12.0f))

            uiSettings.run {
                isRotateGesturesEnabled = false
                isTiltGesturesEnabled = false
            }

            ClusterManager<SegmentClusterItem>(this@MoreClusteringActivity, this).let {
                it.renderer = MoreSegmentClusterRenderer(this@MoreClusteringActivity, this, it)
                setOnCameraIdleListener(it)
                setOnMarkerClickListener(it)

                Segment.values().forEach { segment ->
                    it.addItem(SegmentClusterItem(segment))
                }
            }

            setInfoWindowAdapter(SegmentInfoWindowAdapter(this@MoreClusteringActivity))
        }
    }
}
