package com.example.strawpoll.domain

import android.os.Parcelable
import com.example.strawpoll.network.dtos.AnswerDTO
import com.example.strawpoll.persistence.entities.DatabaseAnswer
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.lang.IllegalArgumentException

class Answer : Serializable {
    private var _id: Int = 0
    var id: Int
        get() = _id
        set(value) {
            require(value >= 0) { "Id ongeldig" }
            _id = value
        }

    private var _answer = ""
    var answer: String
        get() = _answer
        set(value) {
            require(value.isNotBlank()) { "Answer cannot be empty" }
            _answer = value
        }

    private var _amountVoted: Int = 0
    var amountVoted: Int
        get() = _amountVoted
        set(value) {
            require(value >= 0) { "The voted amount cannot be below 0" }
            _amountVoted = value
        }

    constructor(id: Int, answer: String, amountVoted: Int) {
        this.id = id
        this.answer = answer
        this.amountVoted = amountVoted
    }
}

fun List<Answer>.asDTOModel(): List<AnswerDTO> {
    return map {
        AnswerDTO(
            answerId = it.id,
            answerString = it.answer,
            amountVoted = it.amountVoted
        )
    }
}