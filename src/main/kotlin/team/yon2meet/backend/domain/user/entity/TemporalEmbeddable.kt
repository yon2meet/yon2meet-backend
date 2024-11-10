package team.yon2meet.backend.domain.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class TemporalEmbeddable(
    @Column(name = "temporal_username")
    val username: String,

    @Column(name = "temporal_hashed_password")
    val hashedPassword: ByteArray,
)
