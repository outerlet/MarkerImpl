package com.example.markerimpl.data

import com.example.markerimpl.R
import com.google.android.gms.maps.model.LatLng

enum class Station(val position: LatLng, val title: String, val snippet: String, val address: String, val imageResId: Int) {
    Osaka(
        LatLng(34.702485, 135.495951),
        "大阪駅",
        "JR西日本 大阪環状線の駅",
        "大阪府大阪市北区",
        R.drawable.station_1
    ),
    Umeda(
        LatLng(34.705027, 135.498427),
        "梅田駅",
        "阪急電鉄の駅",
        "大阪市北区芝田一丁目1番2号",
        R.drawable.station_2
    ),
    HigashiUmeda(
        LatLng(34.70081, 135.499581),
        "東梅田駅",
        "Osaka Metro 谷町線の駅",
        "大阪府大阪市北区曽根崎二丁目",
        R.drawable.station_3
    ),
    NishiUmeda(
        LatLng(34.699882, 135.49556),
        "西梅田駅",
        "Osaka Metro 四つ橋線の駅",
        "大阪府大阪市北区梅田二丁目",
        R.drawable.station_4
    ),
    UnivalsalCity(
        LatLng(34.667823,135.438530),
        "ユニバーサルシティ駅",
        "JR西日本 JRゆめ咲線の駅",
        "大阪府大阪市此花区島屋六丁目",
        R.drawable.station_1
    ),
    Tenoji(
        LatLng(34.647401,135.513997),
        "天王寺駅",
        "JR西日本・Osaka Metroの駅",
        "大阪府大阪市天王寺区・阿倍野区",
        R.drawable.station_2
    ),
    Kyobashi(
        LatLng(34.696425,135.534102),
        "京橋駅",
        "JR西日本・京阪電気鉄道・Osaka Metroの駅",
        "大阪府大阪市城東区・都島区",
        R.drawable.station_3
    )
}