package com.example.strawpoll.ui.list

import android.app.Application
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

enum class StrawpollApiStatus { LOADING, ERROR, DONE }

class ListViewModel(application: Application) :
    AndroidViewModel(application) {

    //Coroutines
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    //

    //Navigation property
    private val _navigateToPoll = MutableLiveData<Strawpoll>()
    val navigateToPoll
        get() = _navigateToPoll

    fun onPollClick(poll: Strawpoll) {
        _navigateToPoll.value = poll
    }

    fun onPollClickNavigated() {
        _navigateToPoll.value = null
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
    val strawpolls = strawpollRepository.strawpolls
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