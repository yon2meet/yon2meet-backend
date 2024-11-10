package team.yon2meet.backend.domain.user.controller.request

data class KakaoLoginRequest(
    val redirectUri: String,
    val code: String,
)
