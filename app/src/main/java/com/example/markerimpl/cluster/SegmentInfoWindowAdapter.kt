package com.example.markerimpl.cluster

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.markerimpl.R
import com.example.markerimpl.data.Segment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class SegmentInfoWindowAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker): View? = null

    override fun getInfoWindow(marker: Marker): View =
        LayoutInflater.from(context).inflate(R.layout.info_window_segment, null, false).apply {
            val segment = marker.tag as Segment

            findViewById<ImageView>(R.id.imageView).setImageResource(segment.imageResId)
            findViewById<TextView>(R.id.textTitle).text = segment.title
            findViewById<TextView>(R.id.textFlowerName).text = segment.flowerName
        }
}