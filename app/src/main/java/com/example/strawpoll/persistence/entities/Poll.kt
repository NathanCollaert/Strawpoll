package com.example.strawpoll.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "poll_table")
data class Poll(

    @PrimaryKey(autoGenerate = true)
    var pollId: Long = 0L,

    var question: String = "",

    //var answers: List<String> = listOf<String>(),

    @ColumnInfo(name = "creation_date")
    var creationDate: Long = 0L,

    var private: Boolean = false
)