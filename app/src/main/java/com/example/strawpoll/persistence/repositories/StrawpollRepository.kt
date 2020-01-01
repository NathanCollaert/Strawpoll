package com.example.strawpoll.persistence.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.strawpoll.domain.Strawpoll
import com.example.strawpoll.network.StrawpollApi
import com.example.strawpoll.network.dtos.asDBModel
import com.example.strawpoll.persistence.StrawpollDatabase
import com.example.strawpoll.persistence.entities.DatabaseAnswer
import com.example.strawpoll.persistence.entities.DatabaseVotedUUID
import com.example.strawpoll.persistence.entities.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StrawpollRepository(private val database: StrawpollDatabase) {

    val strawpolls: LiveData<List<Strawpoll>> =
        Transformations.map(database.strawpollDao.getPolls()) {
            it.asDomainModel()
        }

    suspend fun refreshStrawpolls() {
        withContext(Dispatchers.IO) {
            val strawpolls = StrawpollApi.retrofitService.getProperties().await()

            strawpolls.forEach{e ->
                val answers = e.answers.map {
                    DatabaseAnswer(
                        id = it.answerId,
                        answer = it.answerString,
                        amountVoted = it.amountVoted,
                        strawpollId = e.strawpollId
                    )
                }

                val alreadyVoted = e.alreadyVoted.map {
                    DatabaseVotedUUID(
                        id = it.votedUUIDId,
                        uuid = it.uuid,
                        strawpollId = e.strawpollId
                    )
                }

                database.strawpollDao.insertAllAnswers(*answers.toTypedArray())
                database.strawpollDao.insertAllAlreadyVoted(*alreadyVoted.toTypedArray())
                database.strawpollDao.insertAllPolls(*strawpolls.asDBModel().toTypedArray())
            }
        }
    }
}