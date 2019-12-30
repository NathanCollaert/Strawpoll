package com.example.strawpoll.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.strawpoll.persistence.daos.PollDao
import com.example.strawpoll.persistence.entities.Poll

@Database(entities = [Poll::class], version = 1, exportSchema = false)
abstract class StrawpollDatabase : RoomDatabase() {

    abstract val pollDao: PollDao

    companion object {
        @Volatile
        private var INSTANCE: StrawpollDatabase? = null

        fun getInstance(context: Context): StrawpollDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StrawpollDatabase::class.java,
                        "strawpoll_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}