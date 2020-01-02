package com.example.strawpoll.ui.result

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.strawpoll.domain.Answer
import com.example.strawpoll.domain.Strawpoll

class ResultViewModel(application: Application, poll: Strawpoll) :
    AndroidViewModel(application) {

    //Data
    private val _strawpoll = MutableLiveData<Strawpoll>()
    val strawpoll: LiveData<Strawpoll>
        get() = _strawpoll

    private val _answers = MutableLiveData<List<Answer>>()
    val answers: LiveData<List<Answer>>
        get() = _answers
    //

    init {
        _strawpoll.value = poll
        _answers.value = poll.answers.sortedByDescending { e -> e.amountVoted }
    }
}