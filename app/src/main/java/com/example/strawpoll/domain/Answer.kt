package com.example.strawpoll.domain

import android.os.Parcelable
import com.example.strawpoll.persistence.entities.DatabaseAnswer
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer (
    val id : Int,
    val answer : String,
    val amountVoted : Int
):Parcelable