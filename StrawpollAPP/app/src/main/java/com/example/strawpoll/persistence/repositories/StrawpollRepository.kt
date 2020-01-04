package com.example.strawpoll.persistence.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.strawpoll.domain.Strawpoll
import com.example.strawpoll.domain.asDTOModel
import com.example.strawpoll.network.StrawpollApi
import com.example.strawpoll.network.dtos.StrawpollDTO
import com.example.strawpoll.network.dtos.asDBModel
import com.example.strawpoll.persistence.StrawpollDatabase
import com.example.strawpoll.persistence.entities.DatabaseAnswer
import com.example.strawpoll.persistence.entities.DatabaseVotedUUID
import com.example.strawpoll.persistence.entities.asDomainModel
import com.example.strawpoll.persistence.entities.asDomainModelList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StrawpollRepository(private val database: StrawpollDatabase) {

    val strawpolls: LiveData<List<Strawpoll>> =
        Transformations.map(database.strawpollDao.getPolls()) {
            it.asDomainModelList()
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

    suspend fun updatePoll(poll: Strawpoll) {
        withContext(Dispatchers.IO) {
            val pollDTO = poll.asDTOModel()
            val result = StrawpollApi.retrofitService.updatePoll(poll.id, pollDTO).await()

            if (result.isSuccessful) {
                pollResponseToDB(result.body()!!, poll.id)
            }
        }
    }

    suspend fun addPoll(poll: Strawpoll): Int {
        var sp: Int = 0
        withContext(Dispatchers.IO) {
            val pollDTO = poll.asDTOModel()
            val result = StrawpollApi.retrofitService.postPoll(pollDTO).await()

            if (result.isSuccessful) {
                pollResponseToDB(result.body()!!, result.body()!!.strawpollId)
                sp = result.body()!!.strawpollId
            }
        }
        return sp
    }

    suspend fun getPoll(id: Int): Strawpoll? {
        var poll : Strawpoll? = null
        withContext(Dispatchers.IO) {
            poll = database.strawpollDao.getPoll(id).asDomainModel()
        }
        return poll
    }

    private fun pollResponseToDB(body: StrawpollDTO, id: Int) {
        val answersDB = body.answers.map {
            DatabaseAnswer(
                id = it.answerId,
                answer = it.answerString,
                amountVoted = it.amountVoted,
                strawpollId = id
            )
        }
        val alreadyVotedUUIDsDB = body.alreadyVoted.map {
            DatabaseVotedUUID(
                id = it.votedUUIDId,
                uuid = it.uuid,
                strawpollId = id
            )
        }
        database.strawpollDao.insertAllAnswers(*answersDB.toTypedArray())
        database.strawpollDao.insertAllAlreadyVoted(*alreadyVotedUUIDsDB.toTypedArray())
        database.strawpollDao.insertPoll(body.asDBModel())
    }
}