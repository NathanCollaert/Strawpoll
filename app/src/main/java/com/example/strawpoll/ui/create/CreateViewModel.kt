package com.example.strawpoll.ui.create

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.strawpoll.persistence.entities.DatabaseAnswer

class CreateViewModel : ViewModel(){

    private lateinit var question:String
    private lateinit var answers:MutableList<DatabaseAnswer>

    init{
        Log.i("CreateViewModel", "Model created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("CreateViewModel", "Model destroyed!")
    }
}