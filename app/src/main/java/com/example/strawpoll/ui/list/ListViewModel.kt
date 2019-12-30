package com.example.strawpoll.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.strawpoll.network.StrawpollApi
import com.example.strawpoll.domain.StrawpollProperty
import com.example.strawpoll.persistence.daos.PollDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class StrawpollApiStatus { LOADING, ERROR, DONE }

class ListViewModel(val database: PollDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val polls = database.getAllPolls()

    private val _navigateToPoll = MutableLiveData<StrawpollProperty>()
    val navigateToPoll
        get() = _navigateToPoll

    private val _status = MutableLiveData<StrawpollApiStatus>()
    val status: LiveData<StrawpollApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<StrawpollProperty>>()
    val properties: LiveData<List<StrawpollProperty>>
        get() = _properties

    init {
        getStrawpolls()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onPollClick(poll:StrawpollProperty) {
        _navigateToPoll.value = poll
    }

    fun onPollClickNavigated() {
        _navigateToPoll.value = null
    }

    private fun getStrawpolls() {
        uiScope.launch {
            var getPropertiesDeferred = StrawpollApi.retrofitService.getProperties()
            try {
                _status.value = StrawpollApiStatus.LOADING
                var listResult = getPropertiesDeferred.await()
                _properties.value = listResult
                _status.value = StrawpollApiStatus.DONE
            } catch (t: Throwable) {
                _status.value = StrawpollApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }
}