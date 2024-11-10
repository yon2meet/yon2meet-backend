package team.yon2meet.backend.domain.user.service

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import team.yon2meet.backend.common.dto.UserDto
import team.yon2meet.backend.domain.user.entity.TemporalEmbeddable
import team.yon2meet.backend.domain.user.entity.User
import team.yon2meet.backend.domain.user.repository.UserRepository
import team.yon2meet.backend.domain.user.util.PasswordUtil
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

    @Transactional(readOnly = true)
    fun getUserByTemporalUsernameAndPassword(username: String, password: String): UserDto? {
        val user = userRepository.findByTemporalUsername(username)
            ?: return null

        if (!PasswordUtil.isPasswordMatch(password, user.temporal!!.hashedPassword))
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다.")

        return UserDto(user)
    }

    @Transactional
    fun createUser(
        kakaoUserId: String?,
        nickname: String?,
        loginType: LoginType,
        temporal: TemporalEmbeddable?,
    ): UserDto {
        val user = User(
            kakaoUserId = kakaoUserId,
            nickname = nickname,
            loginType = loginType,
            temporal = temporal,
        )
        userRepository.save(user)
        return UserDto(user)
    }

    fun getById(userId: Long): UserDto {
        val user = userRepository.findById(userId).orElseThrow()
        return UserDto(user)
    }
}
