package com.example.strawpoll.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.strawpoll.domain.Answer
import com.example.strawpoll.persistence.StrawpollDatabase
import com.example.strawpoll.persistence.entities.DatabaseAnswer
import com.example.strawpoll.persistence.entities.DatabaseStrawpoll
import com.example.strawpoll.persistence.entities.DatabaseVotedUUID
import com.example.strawpoll.persistence.entities.StrawpollAllAnswersAndVoters

@Dao
interface StrawpollDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPolls(vararg strawpolls: DatabaseStrawpoll)

    @Query("SELECT * FROM databasestrawpoll ORDER BY id DESC")
    fun getPolls(): LiveData<List<StrawpollAllAnswersAndVoters>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAnswers(vararg answers: DatabaseAnswer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAlreadyVoted(vararg alreadyVoted: DatabaseVotedUUID)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPoll(poll: DatabaseStrawpoll)
}