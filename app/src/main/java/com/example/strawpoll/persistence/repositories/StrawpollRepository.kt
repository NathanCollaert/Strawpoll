package com.example.strawpoll.persistence.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.strawpoll.domain.Strawpoll
import com.example.strawpoll.domain.asDTOModel
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
            val strawpollsFromAPI = StrawpollApi.retrofitService.getProperties().await()

            strawpollsFromAPI.forEach { e ->
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
            }
            database.strawpollDao.insertAllPolls(*strawpollsFromAPI.asDBModel().toTypedArray())
        }
    }

    suspend fun updatePoll(poll: Strawpoll): Boolean {
        var success = false
        withContext(Dispatchers.IO) {
            val pollDTO = poll.asDTOModel()
            var result = StrawpollApi.retrofitService.updatePoll(poll.id, pollDTO).await()

            if (result.isSuccessful) {
                var resultPoll = result.body()
                var answersDB = resultPoll!!.answers.map {
                    DatabaseAnswer(
                        id = it.answerId,
                        answer = it.answerString,
                        amountVoted = it.amountVoted,
                        strawpollId = pollDTO.strawpollId
                    )
                }
                var alreadyVotedUUIDsDB = resultPoll.alreadyVoted.map {
                    DatabaseVotedUUID(
                        id = it.votedUUIDId,
                        uuid = it.uuid,
                        strawpollId = pollDTO.strawpollId
                    )
                }
                database.strawpollDao.insertAllAnswers(*answersDB.toTypedArray())
                database.strawpollDao.insertAllAlreadyVoted(*alreadyVotedUUIDsDB.toTypedArray())
                database.strawpollDao.insertPoll(resultPoll.asDBModel())
            }

            success = result.isSuccessful
        }
        return success
    }
}