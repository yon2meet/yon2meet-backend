package team.yon2meet.backend.domain.user.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.yon2meet.backend.common.dto.UserDto
import team.yon2meet.backend.configuration.aop.annotation.UserId
import team.yon2meet.backend.domain.user.service.UserService

@RestController
@RequestMapping("/me")
class UserController(
    private val userService: UserService,
) {
    @GetMapping
    fun getMe(
        @UserId userId: Long,
    ): UserDto {
        return userService.getById(userId)
    }
}
