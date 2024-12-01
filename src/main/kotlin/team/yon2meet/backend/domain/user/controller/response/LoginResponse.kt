package team.yon2meet.backend.domain.user.controller.response

import team.yon2meet.backend.common.dto.UserDto

data class LoginResponse(
    val accessToken: String,
    val userResponse: UserDto,
)
