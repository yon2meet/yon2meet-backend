package team.yon2meet.backend.domain.user.controller.response

data class UserResponse(
    val id: Long,
    val kakaoUserId: String?,
    val nickname: String?,
)
