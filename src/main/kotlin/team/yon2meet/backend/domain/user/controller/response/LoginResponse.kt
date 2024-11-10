package team.yon2meet.backend.domain.user.controller.response

data class LoginResponse(
    val accessToken: String,
    val userResponse: UserResponse,
)
