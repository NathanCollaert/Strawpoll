package com.example.strawpoll.domain

import android.os.Parcelable
import com.example.strawpoll.network.dtos.StrawpollDTO
import com.example.strawpoll.persistence.entities.DatabaseStrawpoll
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.LocalDateTime

class Strawpoll : Serializable {
    private var _id: Int = 0
    var id: Int
        get() = _id
        set(value) {
            require(value >= 0) { "Id ongeldig" }
            _id = value
        }

    private var _question: String = ""
    var question: String
        get() = _question
        set(value) {
            require(value.isNotBlank()) { "Question cannot be empty" }
            _question = value
        }

    private var _dateCreated: LocalDateTime = LocalDateTime.now()
    var dateCreated: LocalDateTime
        get() = _dateCreated
        set(value) {
            _dateCreated = value
        }

    private var _answers: MutableList<Answer> = mutableListOf()
    var answers: MutableList<Answer>
        get() = _answers
        set(value) {
            require(value.size >= 2) { "Strawpoll must have atleast 2 answers" }
            _answers = value
        }

    private var _alreadyVoted: MutableList<VotedUUID> = mutableListOf()
    var alreadyVoted: MutableList<VotedUUID>
        get() = _alreadyVoted
        set(value) {
            _alreadyVoted = value
        }

    constructor(
        id: Int,
        question: String,
        dateCreated: LocalDateTime,
        answers: MutableList<Answer>,
        alreadyVoted: MutableList<VotedUUID>
    ) {
        this.id = id
        this.question = question
        this.dateCreated = dateCreated
        this.answers = answers
        this.alreadyVoted = alreadyVoted
    }
}

fun Strawpoll.asDTOModel(): StrawpollDTO {
    return StrawpollDTO(
        strawpollId = this.id,
        question = this.question,
        dateCreated = this.dateCreated.toString(),
        answers = this.answers.asDTOModel(),
        alreadyVoted = this.alreadyVoted.asDTOModel()
    )
}