package com.example.strawpoll.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VotedUUIDProperty(
    @Json(name = "votedUUIDId") val id: Int,
    val uuid: String
) : Parcelable