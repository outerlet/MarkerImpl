package com.example.markerimpl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.markerimpl.data.Station
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions

class MarkerVarietyActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map.apply {
            moveCamera(CameraUpdateFactory.newLatLngZoom(Station.Osaka.position, 16.0f))

            // ドラッグ可能なマーカー
            Station.Osaka.let {
                addMarker(MarkerOptions()
                    .title(it.title)
                    .snippet(it.snippet)
                    .position(it.position)
                    .draggable(true)
                    .zIndex(2.0f)
                ).tag = it
            }

            // マーカーのピンが透過状態＋90°回転して表示
            Station.Umeda.let {
                addMarker(MarkerOptions()
                    .title(it.title)
                    .snippet(it.snippet)
                    .position(it.position)
                    .alpha(0.5f)
                    .rotation(90.0f)
                    .zIndex(2.0f)
                ).tag = it
            }

            // ピンの色を青に変更。ピンの表示も、チルトや回転に合わせて変わる
            Station.HigashiUmeda.let {
                addMarker(MarkerOptions()
                    .title(it.title)
                    .snippet(it.snippet)
                    .position(it.position)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                    .flat(true)
                    .zIndex(2.0f)
                ).tag = it
            }

            Station.NishiUmeda.let {
                addMarker(MarkerOptions()
                    .title(it.title)
                    .snippet(it.snippet)
                    .position(it.position)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                    .zIndex(2.0f)
                ).tag = it
            }
        }
    }
}
