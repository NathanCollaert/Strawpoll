package com.example.strawpoll.ui.vote

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.strawpoll.domain.Strawpoll

class VoteViewModelFactory(
    private val poll: Strawpoll,
    private val application: Application
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VoteViewModel::class.java)) {
            return VoteViewModel(poll, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}