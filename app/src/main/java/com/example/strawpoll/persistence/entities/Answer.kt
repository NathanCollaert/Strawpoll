package com.example.strawpoll.persistence.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answer_table")
data class Answer(

    @PrimaryKey(autoGenerate = true)
    var answerId: Long = 0L,

    var answer: String = "",

    var voteAmount: Int = 0
)