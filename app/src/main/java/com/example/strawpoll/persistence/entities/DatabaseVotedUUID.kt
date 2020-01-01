package com.example.strawpoll.persistence.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.strawpoll.domain.VotedUUID

@Entity
data class DatabaseVotedUUID constructor(
    @PrimaryKey
    val id: Int,
    val uuid: String,
    val strawpollId: Int
)