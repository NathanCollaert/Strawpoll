package com.example.strawpoll.network.dtos

import com.example.strawpoll.persistence.entities.DatabaseStrawpoll
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StrawpollDTO(
    val strawpollId: Int,
    val question: String,
    val dateCreated: String,
    val answers: List<AnswerDTO>,
    val alreadyVoted: List<VotedUUIDDTO>
)

fun List<StrawpollDTO>.asDBModel(): List<DatabaseStrawpoll> {
    return map {
        DatabaseStrawpoll(
            id = it.strawpollId,
            question = it.question,
            dateCreated = it.dateCreated
        )
    }
}

fun StrawpollDTO.asDBModel(): DatabaseStrawpoll {
    return DatabaseStrawpoll(
        id = this.strawpollId,
        question = this.question,
        dateCreated = this.dateCreated
    )

}