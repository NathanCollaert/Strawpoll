package com.example.strawpoll.persistence.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.strawpoll.domain.Answer

@Entity
data class DatabaseAnswer constructor(
    @PrimaryKey
    val id: Int,
    val answer: String,
    val amountVoted: Int,
    val strawpollId: Int
)