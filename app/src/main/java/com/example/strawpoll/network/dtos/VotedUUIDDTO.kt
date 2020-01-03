package com.example.strawpoll.network.dtos

import com.example.strawpoll.domain.VotedUUID
import com.example.strawpoll.persistence.entities.DatabaseVotedUUID
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VotedUUIDDTO(
    val votedUUIDId: Int,
    val uuid: String
)

fun List<VotedUUIDDTO>.asDomainModel(): List<VotedUUID> {
    return map {
        VotedUUID(
            id = it.votedUUIDId,
            uuid = it.uuid
        )
    }
}