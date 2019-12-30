package com.example.strawpoll.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.strawpoll.persistence.entities.Poll

@Dao
interface PollDao {

    @Insert
    fun insert(poll: Poll)

    @Update
    fun update(poll: Poll)

    @Query("SELECT * from poll_table where pollId = :key")
    fun get(key: Long): Poll

    @Query("DELETE FROM poll_table")
    fun clear()

    @Query("SELECT * FROM poll_table ORDER BY pollId DESC")
    fun getAllPolls(): LiveData<List<Poll>>
}