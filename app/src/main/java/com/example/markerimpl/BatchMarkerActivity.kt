package com.example.markerimpl

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.markerimpl.data.Segment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ui.IconGenerator

class BatchMarkerActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var _map: GoogleMap

    private lateinit var _generator: IconGenerator
    private lateinit var _textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        _generator = IconGenerator(this).apply {
            val view = layoutInflater.inflate(R.layout.marker_batch, null, false).apply {
                _textView = findViewById(R.id.textView)
            }

            setBackground(null)
            setContentView(view)
        }

        val fragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        _map = map.apply {
            uiSettings.run {
                isTiltGesturesEnabled = false
                isRotateGesturesEnabled = false
            }

            Segment.values().forEachIndexed { idx, seg ->
                if (idx == 0) {
                    moveCamera(CameraUpdateFactory.newLatLngZoom(seg.coordinate, 14.0f))
                }

                _textView.text = idx.toString()

                addMarker(MarkerOptions()
                    .title(seg.title)
                    .position(seg.coordinate)
                    .anchor(0.5f, 0.85f)
                    .icon(BitmapDescriptorFactory.fromBitmap(_generator.makeIcon()))
                    .zIndex(1.1f))
            }
        }
    }
}
