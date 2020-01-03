package com.example.strawpoll.network.dtos

import com.example.strawpoll.domain.Answer
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnswerDTO(
    val answerId:Int,
    val answerString:String,
    val amountVoted: Int
)

fun List<AnswerDTO>.asDomainModel(): List<Answer> {
    return map {
        Answer(
            id = it.answerId,
            answer = it.answerString,
            amountVoted = it.amountVoted
        )
    }
}