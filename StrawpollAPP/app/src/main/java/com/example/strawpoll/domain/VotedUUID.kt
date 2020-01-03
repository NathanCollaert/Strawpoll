package com.example.strawpoll.domain

import android.os.Parcelable
import com.example.strawpoll.network.dtos.VotedUUIDDTO
import com.example.strawpoll.persistence.entities.DatabaseVotedUUID
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VotedUUID(
    val id: Int,
    val uuid: String
) : Parcelable

fun List<VotedUUID>.asDTOModel(): List<VotedUUIDDTO> {
    return map {
        VotedUUIDDTO(
            votedUUIDId = it.id,
            uuid = it.uuid
        )
    }
}