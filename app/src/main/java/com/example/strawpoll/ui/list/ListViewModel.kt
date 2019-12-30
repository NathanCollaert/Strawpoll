package com.example.strawpoll.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.strawpoll.domain.StrawpollApi
import com.example.strawpoll.domain.StrawpollApiService
import com.example.strawpoll.persistence.daos.PollDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel(val database: PollDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val polls = database.getAllPolls()

    private val _navigateToPoll = MutableLiveData<Long>()
    val navigateToPoll
        get() = _navigateToPoll

    private val _response = MutableLiveData<String>()
    val response
        get() = _response

    init {
        getStrawpolls()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onPollClick(id: Long) {
        _navigateToPoll.value = id
    }

    fun onPollClickNavigated(){
        _navigateToPoll.value = null
    }

    private fun getStrawpolls(){
        StrawpollApi.retrofitService.getProperties().enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }
        })
    }
}