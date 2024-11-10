package team.yon2meet.backend.domain.user.facade

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.yon2meet.backend.common.dto.UserDto
import team.yon2meet.backend.domain.user.service.KakaoAuthService
import team.yon2meet.backend.domain.user.service.KakaoOpenIdConnectService
import team.yon2meet.backend.domain.user.service.UserService
import team.yon2meet.backend.enums.LoginType

@Service
class KakaoLoginFacade(
    private val kakaoAuthService: KakaoAuthService,
    private val kakaoOpenIdConnectService: KakaoOpenIdConnectService,
    private val userService: UserService,
) {
    @Transactional
    fun login(
        redirectUri: String,
        code: String,
    ): UserDto {
        val kakaoAuthLoginResponse = kakaoAuthService.login(
            redirectUri = redirectUri,
            code = code,
        )

        val (kakaoUserId, nickname) = kakaoOpenIdConnectService.decodeClaims(kakaoAuthLoginResponse.id_token!!)

        val user = userService.getUserByKakaoUserId(kakaoUserId)
            ?: userService.createUser(
                kakaoUserId = kakaoUserId,
                nickname = nickname,
                loginType = LoginType.KAKAO
            )

        return user
    }
}
