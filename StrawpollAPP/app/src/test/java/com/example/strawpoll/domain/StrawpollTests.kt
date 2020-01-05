package com.example.strawpoll.domain

import com.example.strawpoll.data.DummyContext
import org.junit.Assert
import org.junit.Test
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

class StrawpollTests {

    val _ctx = DummyContext()

    @Test(expected = IllegalArgumentException::class)
    fun poll_IdOngeldig_Exception() {
        _ctx.poll.id = -1
    }

    @Test
    fun poll_Id_Correct() {
        _ctx.poll.id = 1
        Assert.assertEquals(1, _ctx.poll.id)
    }

    @Test(expected = IllegalArgumentException::class)
    fun poll_QuestionEmpty_Exception() {
        _ctx.poll.question = ""
    }

    @Test(expected = IllegalArgumentException::class)
    fun poll_QuestionWhiteSpace_Exception() {
        _ctx.poll.question = "               "
    }

    @Test
    fun poll_Question_Correct() {
        _ctx.poll.question = "This is a question"
        Assert.assertEquals("This is a question", _ctx.poll.question)
    }

    @Test
    fun poll_DateCreated_Correct() {
        val dateNow = LocalDateTime.now()
        _ctx.poll.dateCreated = dateNow
        Assert.assertEquals(dateNow, _ctx.poll.dateCreated)
    }

    @Test(expected = IllegalArgumentException::class)
    fun poll_AnswersEmpty_Exception() {
        _ctx.poll.answers = mutableListOf()
    }

    @Test(expected = IllegalArgumentException::class)
    fun poll_AnswersLessThen2_Exception() {
        _ctx.poll.answers = mutableListOf(_ctx.answer1)
    }

    @Test
    fun poll_Answers_Correct() {
        _ctx.poll.answers = mutableListOf(_ctx.answer1, _ctx.answer2)
        Assert.assertEquals(2, _ctx.poll.answers.size)
    }

    @Test
    fun poll_AlreadyVoted_Correct() {
        _ctx.poll.alreadyVoted = mutableListOf()
        Assert.assertEquals(0, _ctx.poll.alreadyVoted.size)
    }
}