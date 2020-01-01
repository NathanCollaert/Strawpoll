package com.example.strawpoll.persistence.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.example.strawpoll.domain.Answer
import com.example.strawpoll.domain.Strawpoll
import com.example.strawpoll.domain.VotedUUID
import com.example.strawpoll.network.dtos.StrawpollDTO
import java.time.LocalDateTime

class StrawpollAllAnswersAndVoters {
    @Embedded
    var strawpoll: DatabaseStrawpoll? = null

    @Relation(parentColumn = "id", entityColumn = "strawpollId")
    var answers: List<DatabaseAnswer> = emptyList()

    @Relation(parentColumn = "id", entityColumn = "strawpollId")
    var alreadyVoted: List<DatabaseVotedUUID> = emptyList()
}

fun List<StrawpollAllAnswersAndVoters>.asDomainModel(): List<Strawpoll> {
    return map{
        Strawpoll(
            id = it.strawpoll!!.id,
            question = it.strawpoll!!.question,
            dateCreated = LocalDateTime.parse(it.strawpoll!!.dateCreated),
            answers = it.answers.answersDBToDomain(),
            alreadyVoted = it.alreadyVoted.votedUUIDDBToDomain()
        )
    }
}

fun List<DatabaseAnswer>.answersDBToDomain(): List<Answer> {
    return map {
        Answer(
            id = it.id,
            answer = it.answer,
            amountVoted = it.amountVoted
        )
    }
}

fun List<DatabaseVotedUUID>.votedUUIDDBToDomain(): List<VotedUUID> {
    return map {
        VotedUUID(
            id = it.id,
            uuid = it.uuid
        )
    }
}