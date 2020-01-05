package com.example.strawpoll.domain

import com.example.strawpoll.data.DummyContext
import org.junit.Test
import java.lang.IllegalArgumentException

class VotedUUIDTests {

    private val _ctx = DummyContext()

    @Test(expected = IllegalArgumentException::class)
    fun vote_IdOngeldig_Exception() {
        _ctx.vote1.id = -1
    }

    @Test
    fun vote_Id_Correct() {
        _ctx.vote1.id = 1
    }

    @Test(expected = IllegalArgumentException::class)
    fun vote_UuidEmpty_Exception() {
        _ctx.vote1.uuid = ""
    }

    @Test(expected = IllegalArgumentException::class)
    fun vote_UuidWhiteSpace_Exception() {
        _ctx.vote1.uuid = "           "
    }

    @Test
    fun vote_Uuid_Correct() {
        _ctx.vote1.uuid = "1b98306b2f7794c8"
    }
}