package team.yon2meet.backend.common.dto

import team.yon2meet.backend.domain.user.entity.User

data class UserDto(
    val id: Long,
    val kakaoUserId: String?,
    val nickname: String?,
) {
    constructor(user: User) : this(
        id = user.id!!,
        kakaoUserId = user.kakaoUserId,
        nickname = user.nickname,
    )
}
