package team.yon2meet.backend.common.dto

import team.yon2meet.backend.domain.user.entity.TemporalEmbeddable

data class TemporalDto(
    val username: String,
) {
    constructor(temporal: TemporalEmbeddable) : this(
        username = temporal.username,
    )
}
