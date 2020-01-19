package com.atdev.cashingandroid.pojo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.atdev.cashingandroid.pojo.database.dao.VideoDao

@Database(entities = [DatabaseVideo::class], version = 1)
abstract class VIdeosDatabase : RoomDatabase() {

    abstract val VideoDao: VideoDao
}

private lateinit var InstanceDB: VIdeosDatabase

fun getDB(context: Context): VIdeosDatabase {

    synchronized(VIdeosDatabase::class.java) {

        if (!::InstanceDB.isInitialized) {

            InstanceDB = Room.databaseBuilder(
                context.applicationContext,
                VIdeosDatabase::class.java,
                "VIdeos"
            ).build()
        }

    }

    return InstanceDB
}