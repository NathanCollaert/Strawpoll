package com.example.strawpoll.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnswerProperty (
    @Json(name ="answerId") val id : Int,
    @Json(name = "answerString") val answer : String,
    val amountVoted : Int
) : Parcelable