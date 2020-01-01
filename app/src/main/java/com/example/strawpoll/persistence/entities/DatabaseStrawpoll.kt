package com.example.strawpoll.persistence.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.strawpoll.domain.Strawpoll
import com.example.strawpoll.domain.VotedUUID
import com.example.strawpoll.network.dtos.AnswerDTO
import com.example.strawpoll.network.dtos.StrawpollDTO
import com.example.strawpoll.network.dtos.VotedUUIDDTO
import com.example.strawpoll.network.dtos.asDomainModel
import com.example.strawpoll.persistence.StrawpollDatabase

@Entity
data class DatabaseStrawpoll constructor(
    @PrimaryKey
    val id: Int,
    val question: String,
    val dateCreated: String
)