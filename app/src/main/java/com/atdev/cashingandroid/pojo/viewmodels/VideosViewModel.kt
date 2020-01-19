package com.atdev.cashingandroid.pojo.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.atdev.cashingandroid.pojo.database.getDB
import com.atdev.cashingandroid.pojo.repos.VideosRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class VideosViewModel(application: Application) : AndroidViewModel(application) {


    private val viewModelJob = SupervisorJob()


    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDB(application)
    private val videosRepository = VideosRepo(database)


    init {
        viewModelScope.launch {
            videosRepository.refreshVideos()
        }
    }

    val playlist = videosRepository.videos

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(VideosViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VideosViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}