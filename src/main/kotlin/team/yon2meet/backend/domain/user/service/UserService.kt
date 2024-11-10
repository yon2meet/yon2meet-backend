package team.yon2meet.backend.domain.user.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.yon2meet.backend.common.dto.UserDto
import team.yon2meet.backend.domain.user.entity.User
import team.yon2meet.backend.domain.user.repository.UserRepository
import team.yon2meet.backend.enums.LoginType

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    @Transactional(readOnly = true)
    fun getUserByKakaoUserId(kakaoUserId: String): UserDto? {
        val user = userRepository.findByKakaoUserId(kakaoUserId)
            ?: return null

        return UserDto(user)
    }

    @Transactional
    fun createUser(kakaoUserId: String, nickname: String?, loginType: LoginType): UserDto {
        val user = User(
            kakaoUserId = kakaoUserId,
            nickname = nickname,
            loginType = loginType,
        )
        userRepository.save(user)
        return UserDto(user)
    }
}
