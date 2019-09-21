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
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ui.IconGenerator
import kotlinx.android.synthetic.main.activity_marker_batch.*

class MarkerBatchActivity : AppCompatActivity(), OnMapReadyCallback {
    private inner class MarkerHolder(private var marker: Marker, private var batchNumber: Int) {
        fun update() {
            val newNumber = if (batchNumber + 1 > 10) 0 else batchNumber + 1

            marker = _map.addBatchMarker(marker.title, marker.position, newNumber)
            batchNumber = newNumber
        }
    }

    private lateinit var _map: GoogleMap

    private lateinit var _generator: IconGenerator
    private lateinit var _textView: TextView

    private val _markerList = mutableListOf<MarkerHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marker_batch)

        _generator = IconGenerator(this).apply {
            val view = layoutInflater.inflate(R.layout.marker_bath_and_batch, null, false).apply {
                _textView = findViewById(R.id.textView)
            }

            setBackground(null)
            setContentView(view)
        }

        buttonUpdate.setOnClickListener {
            _markerList.forEach { it.update() }
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
                if (seg == Segment.Chuo) {
                    moveCamera(CameraUpdateFactory.newLatLngZoom(seg.coordinate, 12.0f))
                }

                val num = idx % 10

                _markerList += MarkerHolder(addBatchMarker(seg.title, seg.coordinate, num), num)
            }
        }
    }

    private fun GoogleMap.addBatchMarker(title: String, position: LatLng, batchNumber: Int): Marker {
        _textView.text = batchNumber.toString()

        return addMarker(MarkerOptions()
            .title(title)
            .position(position)
            .anchor(0.5f, 0.85f)
            .icon(BitmapDescriptorFactory.fromBitmap(_generator.makeIcon()))
            .zIndex(1.1f)).apply { tag = batchNumber }
    }
}
