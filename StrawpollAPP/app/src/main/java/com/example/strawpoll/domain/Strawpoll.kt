package com.example.strawpoll.domain

import android.os.Parcelable
import com.example.strawpoll.network.dtos.StrawpollDTO
import com.example.strawpoll.persistence.entities.DatabaseStrawpoll
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Strawpoll(
    val id: Int,
    val question: String,
    val dateCreated: LocalDateTime,
    val answers: MutableList<Answer>,
    val alreadyVoted: MutableList<VotedUUID>
) : Parcelable

fun Strawpoll.asDTOModel(): StrawpollDTO {
    return StrawpollDTO(
        strawpollId = this.id,
        question = this.question,
        dateCreated = this.dateCreated.toString(),
        answers = this.answers.asDTOModel(),
        alreadyVoted = this.alreadyVoted.asDTOModel()
    )
}