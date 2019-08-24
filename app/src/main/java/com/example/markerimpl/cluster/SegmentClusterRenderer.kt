package com.example.markerimpl.cluster

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

class SegmentClusterRenderer(context: Context, map: GoogleMap, manager: ClusterManager<SegmentClusterItem>) :
    DefaultClusterRenderer<SegmentClusterItem>(context, map, manager) {
    override fun onClusterItemRendered(item: SegmentClusterItem, marker: Marker) {
        marker.tag = item.segment
        super.onClusterItemRendered(item, marker)
    }

    override fun shouldRenderAsCluster(cluster: Cluster<SegmentClusterItem>?): Boolean {
        // ClusterItemが一定距離内にいくつ集まったらクラスタ化するかをBooleanで返す
        return cluster?.size ?: 0 >= 2
    }
}