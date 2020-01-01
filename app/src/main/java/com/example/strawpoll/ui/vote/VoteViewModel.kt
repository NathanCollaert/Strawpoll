package com.example.strawpoll.ui.vote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.strawpoll.domain.Strawpoll

class VoteViewModel(@Suppress("UNUSED_PARAMETER") strawpoll: Strawpoll, app: Application) :
    AndroidViewModel(app) {

    private val _selectedStrawpoll = MutableLiveData<Strawpoll>()
    val selectedStrawpoll: LiveData<Strawpoll>
        get() = _selectedStrawpoll

    init {
        _selectedStrawpoll.value = strawpoll
    }

}