package team.yon2meet.backend.domain.user.controller

import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.yon2meet.backend.domain.user.controller.request.KakaoLoginRequest
import team.yon2meet.backend.domain.user.controller.request.TemporalLoginRequest
import team.yon2meet.backend.domain.user.controller.response.LoginResponse
import team.yon2meet.backend.domain.user.facade.LoginFacade


@RestController
@RequestMapping("/auth")
class LoginController(
    private val loginFacade: LoginFacade,
) {
    @PutMapping("/login/kakao")
    fun kakaoLogin(@RequestBody kakaoLoginRequest: KakaoLoginRequest): LoginResponse {
        return loginFacade.kakaoLogin(
            redirectUri = kakaoLoginRequest.redirectUri,
            code = kakaoLoginRequest.code,
        )
    }

    @PutMapping("/login/temporal")
    fun temporalLogin(
        @RequestBody temporalLoginRequest: TemporalLoginRequest,
    ): LoginResponse {
        return loginFacade.temporalLogin(
            username = temporalLoginRequest.username,
            password = temporalLoginRequest.password,
        )
    }
}
