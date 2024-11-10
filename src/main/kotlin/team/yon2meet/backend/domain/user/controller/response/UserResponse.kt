package team.yon2meet.backend.domain.user.controller.response

data class UserResponse(
    val id: Long,
    val nickname: String?,
    val kakaoUserId: String?,
    val temporal: UserTemporalResponse?,
)
