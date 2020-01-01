package com.example.strawpoll.ui.create

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.strawpoll.domain.Answer
import com.example.strawpoll.persistence.entities.DatabaseAnswer

class CreateViewModel : ViewModel(){

    private lateinit var question:String
    private val _answers = MutableLiveData<MutableList<Answer>>()
    val answers: LiveData<MutableList<Answer>>
        get() = _answers

    init{
        createAnswerList()
    }

    private fun createAnswerList() {
        _answers.value!!.add(Answer(0,"",0))
        _answers.value!!.add(Answer(0,"",0))
    }

    private fun addAnswerToList(){
        _answers.value!!.add(Answer(0,"",0))
    }

    override fun onCleared() {
        super.onCleared()
    }
}