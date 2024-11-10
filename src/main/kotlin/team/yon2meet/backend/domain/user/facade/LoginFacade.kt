package team.yon2meet.backend.domain.user.facade

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.yon2meet.backend.configuration.security.service.JwtTokenService
import team.yon2meet.backend.domain.user.controller.response.LoginResponse
import team.yon2meet.backend.domain.user.controller.response.UserResponse
import team.yon2meet.backend.domain.user.controller.response.UserTemporalResponse
import team.yon2meet.backend.domain.user.entity.TemporalEmbeddable
import team.yon2meet.backend.domain.user.service.KakaoAuthService
import team.yon2meet.backend.domain.user.service.KakaoOpenIdConnectService
import team.yon2meet.backend.domain.user.service.UserService
import team.yon2meet.backend.domain.user.util.PasswordUtil
import team.yon2meet.backend.enums.LoginType

@Service
class LoginFacade(
    private val kakaoAuthService: KakaoAuthService,
    private val kakaoOpenIdConnectService: KakaoOpenIdConnectService,
    private val userService: UserService,
    private val jwtTokenService: JwtTokenService,
) {
    @Transactional
    fun kakaoLogin(
        redirectUri: String,
        code: String,
    ): LoginResponse {
        val kakaoAuthLoginResponse = kakaoAuthService.login(
            redirectUri = redirectUri,
            code = code,
        )

        val (kakaoUserId, nickname) = kakaoOpenIdConnectService.decodeClaims(kakaoAuthLoginResponse.id_token!!)

        val user = userService.getUserByKakaoUserId(kakaoUserId)
            ?: userService.createUser(
                kakaoUserId = kakaoUserId,
                nickname = nickname,
                loginType = LoginType.KAKAO,
                temporal = null,
            )

        val userTemporalResponse = user.temporal!!.let {
            UserTemporalResponse(
                username = it.username,
            )
        }
        val userResponse = UserResponse(
            id = user.id,
            kakaoUserId = user.kakaoUserId,
            nickname = user.nickname,
            temporal = userTemporalResponse,
        )


        val accessToken = jwtTokenService.createToken(user.id)

        return LoginResponse(
            accessToken = accessToken,
            userResponse = userResponse,
        )
    }

    @Transactional
    fun temporalLogin(
        username: String,
        password: String,
    ): LoginResponse {
        val user = userService.getUserByTemporalUsernameAndPassword(username, password)
            ?: userService.createUser(
                kakaoUserId = null,
                nickname = username,
                loginType = LoginType.TEMPORAL,
                temporal = TemporalEmbeddable(
                    username = username,
                    hashedPassword = PasswordUtil.hashPassword(password),
                ),
            )

        val userTemporalResponse = user.temporal!!.let {
            UserTemporalResponse(
                username = it.username,
            )
        }
        val userResponse = UserResponse(
            id = user.id,
            kakaoUserId = user.kakaoUserId,
            nickname = user.nickname,
            temporal = userTemporalResponse,
        )

        val accessToken = jwtTokenService.createToken(user.id)

        return LoginResponse(
            accessToken = accessToken,
            userResponse = userResponse,
        )
    }
}
