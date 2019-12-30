package com.example.strawpoll.ui.vote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.strawpoll.domain.StrawpollProperty

class VoteViewModel(@Suppress("UNUSED_PARAMETER") strawpollProperty: StrawpollProperty, app: Application) :
    AndroidViewModel(app) {

    private val _selectedStrawpoll = MutableLiveData<StrawpollProperty>()
    val selectedStrawpoll: LiveData<StrawpollProperty>
        get() = _selectedStrawpoll

    init {
        _selectedStrawpoll.value = strawpollProperty
    }

}