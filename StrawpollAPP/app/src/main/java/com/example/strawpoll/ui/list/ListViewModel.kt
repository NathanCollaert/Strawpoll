package com.example.strawpoll.ui.list

import android.annotation.SuppressLint
import android.app.Application
import android.provider.Settings
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.strawpoll.network.StrawpollApi
import com.example.strawpoll.domain.Strawpoll
import com.example.strawpoll.persistence.StrawpollDatabase
import com.example.strawpoll.persistence.daos.StrawpollDao
import com.example.strawpoll.persistence.repositories.StrawpollRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import java.util.stream.Collectors

enum class StrawpollApiStatus { LOADING, ERROR, DONE }

class ListViewModel(application: Application) :
    AndroidViewModel(application) {

    //Coroutines
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    //

    var app = application

    //Navigation property
    private val _navigateToPoll = MutableLiveData<Strawpoll>()
    val navigateToPoll
        get() = _navigateToPoll

    private val _alreadyVoted = MutableLiveData<Boolean>()
    val alreadyVoted
        get() = _alreadyVoted

    @SuppressLint("HardwareIds")
    fun onPollClick(poll: Strawpoll) {
        _alreadyVoted.value =
            poll.alreadyVoted.stream().map { e -> e.uuid }.collect(Collectors.toList()).contains(
                Settings.Secure.getString(
                    app.contentResolver,
                    Settings.Secure.ANDROID_ID
                )
            )
        _navigateToPoll.value = poll
    }

    fun onPollClickNavigated() {
        _navigateToPoll.value = null
        _alreadyVoted.value = false
    }
    //

    //API status
    private val _status = MutableLiveData<StrawpollApiStatus>()
    val status: LiveData<StrawpollApiStatus>
        get() = _status
    //

    //repository
    private val database = StrawpollDatabase.getInstance(application)
    private val strawpollRepository = StrawpollRepository(database)
    //

    //Data
    var strawpolls = strawpollRepository.strawpolls
    //

    init {
        uiScope.launch {
            try {
                _status.value = StrawpollApiStatus.LOADING
                strawpollRepository.refreshStrawpolls()
                _status.value = StrawpollApiStatus.DONE
            } catch (t: Throwable) {
                _status.value = StrawpollApiStatus.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}