package com.example.strawpoll.persistence.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.example.strawpoll.domain.Answer
import com.example.strawpoll.domain.Strawpoll
import com.example.strawpoll.domain.VotedUUID
import java.time.LocalDateTime

class StrawpollAllAnswersAndVoters {
    @Embedded
    var strawpoll: DatabaseStrawpoll? = null

    @Relation(parentColumn = "id", entityColumn = "strawpollId")
    var answers: MutableList<DatabaseAnswer> = ArrayList()

    @Relation(parentColumn = "id", entityColumn = "strawpollId")
    var alreadyVoted: MutableList<DatabaseVotedUUID> = ArrayList()
}

fun List<StrawpollAllAnswersAndVoters>.asDomainModelList(): List<Strawpoll> {
    return map {
        Strawpoll(
            id = it.strawpoll!!.id,
            question = it.strawpoll!!.question,
            dateCreated = LocalDateTime.parse(it.strawpoll!!.dateCreated),
            answers = it.answers.answersDBToDomain(),
            alreadyVoted = it.alreadyVoted.votedUUIDDBToDomain()
        )
    }
}

fun StrawpollAllAnswersAndVoters.asDomainModel(): Strawpoll {
    return Strawpoll(
        id = this.strawpoll!!.id,
        question = this.strawpoll!!.question,
        dateCreated = LocalDateTime.parse(this.strawpoll!!.dateCreated),
        answers = this.answers.answersDBToDomain(),
        alreadyVoted = this.alreadyVoted.votedUUIDDBToDomain()
    )

}

fun MutableList<DatabaseAnswer>.answersDBToDomain(): MutableList<Answer> {
    return map {
        Answer(
            id = it.id,
            answer = it.answer,
            amountVoted = it.amountVoted
        )
    }.toMutableList()
}

fun MutableList<DatabaseVotedUUID>.votedUUIDDBToDomain(): MutableList<VotedUUID> {
    return map {
        VotedUUID(
            id = it.id,
            uuid = it.uuid
        )
    }.toMutableList()
}