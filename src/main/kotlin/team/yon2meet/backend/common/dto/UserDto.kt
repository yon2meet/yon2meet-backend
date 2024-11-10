package team.yon2meet.backend.common.dto

import team.yon2meet.backend.domain.user.entity.User

data class UserDto(
    val id: Long,
    val kakaoUserId: String?,
    val nickname: String?,
    val temporal: TemporalDto?,
) {
    constructor(user: User) : this(
        id = user.id!!,
        kakaoUserId = user.kakaoUserId,
        nickname = user.nickname,
        temporal = user.temporal?.let { TemporalDto(it) },
    )
}
