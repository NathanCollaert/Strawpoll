package com.example.strawpoll.domain

import android.os.Parcelable
import com.example.strawpoll.network.dtos.AnswerDTO
import com.example.strawpoll.persistence.entities.DatabaseAnswer
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer(
    val id: Int,
    val answer: String,
    var amountVoted: Int
) : Parcelable

fun List<Answer>.asDTOModel(): List<AnswerDTO> {
    return map {
        AnswerDTO(
            answerId = it.id,
            answerString = it.answer,
            amountVoted = it.amountVoted
        )
    }
}