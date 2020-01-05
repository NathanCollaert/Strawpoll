package com.example.strawpoll.domain

import com.example.strawpoll.data.DummyContext
import org.junit.Test
import java.lang.IllegalArgumentException

class AnswerTests {

    private val _ctx = DummyContext()

    @Test(expected = IllegalArgumentException::class)
    fun answer_IdOngeldig_Exception() {
        _ctx.answer1.id = -1
    }

    @Test(expected = IllegalArgumentException::class)
    fun answer_answerStringEmpty_Exception() {
        _ctx.answer1.answer = ""
    }

    @Test(expected = IllegalArgumentException::class)
    fun answer_answerStringWhiteSpace_Exception() {
        _ctx.answer1.answer = "               "
    }

    @Test(expected = IllegalArgumentException::class)
    fun answer_amountVotedOngeldid_Exception() {
        _ctx.answer1.amountVoted = -1
    }
}