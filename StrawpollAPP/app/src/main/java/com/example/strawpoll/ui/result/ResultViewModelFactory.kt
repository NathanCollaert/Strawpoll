package com.example.strawpoll.ui.result

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.strawpoll.domain.Strawpoll

class ResultViewModelFactory(private val poll: Strawpoll, private val application: Application) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(application, poll) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}