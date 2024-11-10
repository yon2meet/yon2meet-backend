package team.yon2meet.backend.domain.user.controller

import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.yon2meet.backend.configuration.security.service.JwtTokenService
import team.yon2meet.backend.domain.user.controller.request.KakaoLoginRequest
import team.yon2meet.backend.domain.user.controller.response.KakaoLoginResponse
import team.yon2meet.backend.domain.user.controller.response.UserResponse
import team.yon2meet.backend.domain.user.facade.KakaoLoginFacade


@RestController
@RequestMapping("/auth")
class LoginController(
    private val kakaoLoginFacade: KakaoLoginFacade,
    private val jwtTokenService: JwtTokenService,
) {
    @PutMapping("/login/kakao")
    fun kakaoLogin(@RequestBody kakaoLoginRequest: KakaoLoginRequest): KakaoLoginResponse {
        val userDto = kakaoLoginFacade.login(
            redirectUri = kakaoLoginRequest.redirectUri,
            code = kakaoLoginRequest.code,
        )

        val userResponse = UserResponse(
            id = userDto.id,
            kakaoUserId = userDto.kakaoUserId,
            nickname = userDto.nickname,
        )
        val accessToken = jwtTokenService.createToken(userDto.id)
        return KakaoLoginResponse(
            accessToken = accessToken,
            userResponse = userResponse,
        )
    }
}
