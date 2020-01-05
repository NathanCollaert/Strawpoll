package com.example.strawpoll.data

import com.example.strawpoll.domain.Answer
import com.example.strawpoll.domain.Strawpoll
import com.example.strawpoll.domain.VotedUUID
import java.time.LocalDateTime

class DummyContext {
    val answer1: Answer = Answer(1, "This is the first answer", 100)
    val answer2: Answer = Answer(2, "This is the second answer", 200)
    val vote1: VotedUUID = VotedUUID(1, "1b98306b2f7794c8")
    val vote2: VotedUUID = VotedUUID(2, "thisisatestvoteuuid")
    val poll: Strawpoll = Strawpoll(
        1, "This is the strawpoll question", LocalDateTime.now(),
        mutableListOf(answer1, answer2), mutableListOf(vote1, vote2)
    )
}