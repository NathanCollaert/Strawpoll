package com.example.strawpoll.ui.vote

import android.app.Application
import android.content.Context
import android.telephony.TelephonyManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.strawpoll.domain.Answer
import com.example.strawpoll.domain.Strawpoll
import com.example.strawpoll.domain.VotedUUID
import com.example.strawpoll.persistence.StrawpollDatabase
import com.example.strawpoll.persistence.repositories.StrawpollRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class VoteViewModel(@Suppress("UNUSED_PARAMETER") strawpoll: Strawpoll, app: Application) :
    AndroidViewModel(app) {

    //Coroutines
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    //

    //repository
    private val database = StrawpollDatabase.getInstance(app)
    private val strawpollRepository = StrawpollRepository(database)
    //

    private val _selectedStrawpoll = MutableLiveData<Strawpoll>()
    val selectedStrawpoll: LiveData<Strawpoll>
        get() = _selectedStrawpoll

    private val _answers = MutableLiveData<List<Answer>>()
    val answers: LiveData<List<Answer>>
        get() = _answers

    init {
        _selectedStrawpoll.value = strawpoll
        _answers.value = strawpoll.answers
    }

    fun vote(id: Int) {
        _selectedStrawpoll.value!!.alreadyVoted.add(VotedUUID(0, UUID.randomUUID().toString()))
        _selectedStrawpoll.value!!.answers.stream().filter { e -> e.id == id }.findFirst()
            .orElse(null).amountVoted += 1
        uiScope.launch {
            strawpollRepository.updatePoll(_selectedStrawpoll.value!!)
        }
    }
}