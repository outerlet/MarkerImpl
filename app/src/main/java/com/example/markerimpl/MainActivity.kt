package com.example.markerimpl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun handleClickEvent(view: View) {
        when (view.id) {
            R.id.buttonVariety -> MarkerVarietyActivity::class.java
            R.id.buttonEvents -> MarkerEventActivity::class.java
            R.id.buttonClustering -> ClusteringActivity::class.java
            R.id.buttonMoreClustering -> MoreClusteringActivity::class.java
            R.id.buttonCustomIcon -> CustomIconActivity::class.java
            else -> null
        }?.let {
            startActivity(Intent(this@MainActivity, it))
        }
    }
}
