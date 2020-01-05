package com.example.strawpoll.ui.create

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.strawpoll.domain.Answer
import com.example.strawpoll.domain.Strawpoll
import com.example.strawpoll.domain.VotedUUID
import com.example.strawpoll.persistence.StrawpollDatabase
import com.example.strawpoll.persistence.repositories.StrawpollRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.time.LocalDateTime
import java.util.stream.Collectors

class CreateViewModel(app: Application) : AndroidViewModel(app) {

    var question: String = ""
    var answer1: String = ""
    var answer2: String = ""
    var answer3: String = ""
    var answer4: String = ""
    var answer5: String = ""
    var answer6: String = ""
    var answer7: String = ""
    var answer8: String = ""
    var answer9: String = ""

    private val _strawpoll = MutableLiveData<Strawpoll>()
    val strawpoll: LiveData<Strawpoll>
        get() = _strawpoll

    //Coroutines
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    //

    //repository
    private val database = StrawpollDatabase.getInstance(app)
    private val strawpollRepository = StrawpollRepository(database)
    //

    fun onSubmit() {
        val answers = mutableListOf<String>()
        answers.add(answer1)
        answers.add(answer2)
        answers.add(answer3)
        answers.add(answer4)
        answers.add(answer5)
        answers.add(answer6)
        answers.add(answer7)
        answers.add(answer8)
        answers.add(answer9)

        val answersNotEmpty =
            answers.stream().filter { e -> e.isNotBlank() }.map { e -> Answer(0, e!!, 0) }
                .collect(Collectors.toList())

        if (question.isEmpty()) {
            Toast.makeText(
                getApplication(),
                "You will need to add a question first.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (answersNotEmpty.size < 2) {
            Toast.makeText(
                getApplication(),
                "You will need to add 2 none empty answers first.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        uiScope.launch {
            _strawpoll.value = strawpollRepository.getPoll(
                strawpollRepository.addPoll(
                    Strawpoll(
                        0, question, LocalDateTime.now(), answersNotEmpty,
                        mutableListOf()
                    )
                )
            )
        }
    }

    fun onNavigated() {
        _strawpoll.value = null
    }
}