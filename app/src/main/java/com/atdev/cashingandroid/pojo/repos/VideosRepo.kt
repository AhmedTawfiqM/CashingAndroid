package com.atdev.cashingandroid.pojo.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.atdev.cashingandroid.pojo.database.VIdeosDatabase
import com.atdev.cashingandroid.pojo.database.asDomainModel
import com.atdev.cashingandroid.pojo.models.Video
import com.atdev.cashingandroid.pojo.network.Network
import com.atdev.cashingandroid.pojo.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class VideosRepo(private val database: VIdeosDatabase) {

    /**
     * A playlist of videos that can be shown on the screen.
     */
    val videos: LiveData<List<Video>> =
        Transformations.map(database.VideoDao.getVideos()) {
            it.asDomainModel()
        }

    suspend fun refreshVideos() {

        withContext(Dispatchers.IO) {

            val playlist = Network.devbytes.getPlaylist().await()

            database.VideoDao.InsertAll(*playlist.asDatabaseModel())
        }
    }
}