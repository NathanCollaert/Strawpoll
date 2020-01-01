package com.example.strawpoll.domain

import android.os.Parcelable
import com.example.strawpoll.persistence.entities.DatabaseStrawpoll
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Strawpoll (
    val id: Int,
    val question: String,
    val dateCreated: LocalDateTime,
    val answers: List<Answer>,
    val alreadyVoted: List<VotedUUID>
):Parcelable