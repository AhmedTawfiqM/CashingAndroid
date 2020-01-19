package com.atdev.cashingandroid.pojo.network

import com.atdev.cashingandroid.pojo.database.DatabaseVideo
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos: List<NetworkVideo>)

@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?
)

fun NetworkVideoContainer.asDatabaseModel(): Array<DatabaseVideo> {

    return videos.map {

        DatabaseVideo(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail
        )

    }.toTypedArray()
}
