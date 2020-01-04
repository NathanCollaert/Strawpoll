package com.example.strawpoll.ui.vote

import android.annotation.SuppressLint
import android.app.Application
import android.provider.Settings
import android.util.Log
import android.widget.Toast
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
import java.io.IOException
import java.lang.Exception
import java.util.*


class VoteViewModel(strawpoll: Strawpoll, app: Application) :
    AndroidViewModel(app) {

    //Coroutines
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    //

    private var app = app

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

    val _selectedAnswer = MutableLiveData<Int>()

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean>
        get() = _success

    init {
        _selectedStrawpoll.value = strawpoll
        _answers.value = strawpoll.answers
    }

    fun onNavigated() {
        _success.value = false
        _selectedAnswer.value = null
    }

    @SuppressLint("HardwareIds")
    fun vote() {
        try {
            _selectedStrawpoll.value!!.alreadyVoted.add(
                VotedUUID(
                    0, Settings.Secure.getString(
                        app.contentResolver,
                        Settings.Secure.ANDROID_ID
                    )
                )
            )
            _selectedStrawpoll.value!!.answers.stream()
                .filter { e -> e.id == _selectedAnswer.value }.findFirst()
                .orElse(null).amountVoted += 1
            uiScope.launch {
                strawpollRepository.updatePoll(_selectedStrawpoll.value!!)
                _success.value = true
            }
        } catch (e: IOException) {
            Toast.makeText(
                getApplication(),
                "Something went wrong while voting, please try again.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}