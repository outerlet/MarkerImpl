package com.example.markerimpl.cluster

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.markerimpl.R
import com.example.markerimpl.data.Station
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class StationInfoWindowAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker): View? {
        return setupWindow(marker)
    }

    private fun setupWindow(marker: Marker): View =
        LayoutInflater.from(context).inflate(R.layout.info_window_station, null, false).apply {
            val station = marker.tag as Station

            findViewById<ImageView>(R.id.imageView).setImageResource(station.imageResId)
            findViewById<TextView>(R.id.textTitle).text = marker.title
            findViewById<TextView>(R.id.textSnippet).text = marker.snippet
        }
}