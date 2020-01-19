package com.atdev.cashingandroid.pojo.models

import com.atdev.cashingandroid.pojo.utililities.smartTruncate


data class Video constructor(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String
) {
    val shortDesc: String
        get() = description.smartTruncate(200)


}