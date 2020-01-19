package com.atdev.cashingandroid.pojo.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.atdev.cashingandroid.pojo.database.DatabaseVideo

interface VideoDao {

    @Query("select * from databasevideo")
    fun getVideos(): LiveData<List<DatabaseVideo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertAll(vararg videos: DatabaseVideo)

}