package com.example.strawpoll.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StrawpollProperty (
    @Json(name = "strawpollId") val id : Int,
    val question : String,
    val dateCreated : String,
    val answers : List<AnswerProperty>,
    val alreadyVoted : List<VotedUUIDProperty>
) : Parcelable