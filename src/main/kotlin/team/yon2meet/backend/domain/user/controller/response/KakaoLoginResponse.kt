package team.yon2meet.backend.domain.user.controller.response

data class KakaoLoginResponse(
    val accessToken: String,
    val userResponse: UserResponse,
)
