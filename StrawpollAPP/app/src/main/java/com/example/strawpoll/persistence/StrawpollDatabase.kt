package com.example.strawpoll.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.strawpoll.persistence.daos.StrawpollDao
import com.example.strawpoll.persistence.entities.DatabaseAnswer
import com.example.strawpoll.persistence.entities.DatabaseStrawpoll
import com.example.strawpoll.persistence.entities.DatabaseVotedUUID

@Database(entities = [DatabaseStrawpoll::class, DatabaseAnswer::class, DatabaseVotedUUID::class], version = 11, exportSchema = false)
abstract class StrawpollDatabase : RoomDatabase() {

    abstract val strawpollDao: StrawpollDao

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