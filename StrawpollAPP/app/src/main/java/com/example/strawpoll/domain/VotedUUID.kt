package com.example.strawpoll.domain

import android.os.Parcelable
import com.example.strawpoll.network.dtos.VotedUUIDDTO
import com.example.strawpoll.persistence.entities.DatabaseVotedUUID
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

class VotedUUID : Serializable {
    private var _id: Int = 0
    var id: Int
        get() = _id
        set(value) {
            require(value >= 0) { "Id ongeldig" }
            _id = value
        }

    private var _uuid: String = ""
    var uuid: String
        get() = _uuid
        set(value) {
            require(value.isNotBlank()) { "Uuid cannot be empty" }
            _uuid = value
        }

    constructor(id: Int, uuid: String) {
        this.id = id
        this.uuid = uuid
    }
}

fun List<VotedUUID>.asDTOModel(): List<VotedUUIDDTO> {
    return map {
        VotedUUIDDTO(
            votedUUIDId = it.id,
            uuid = it.uuid
        )
    }
}