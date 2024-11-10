package team.yon2meet.backend.configuration.aop

import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import team.yon2meet.backend.configuration.aop.annotation.UserId
import team.yon2meet.backend.configuration.security.CustomUserDetails
import team.yon2meet.backend.configuration.security.SecurityContextUtil

class UserIdArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(UserId::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): Any {
        val userDetails: CustomUserDetails = SecurityContextUtil.getAuthentication().principal as CustomUserDetails
        return userDetails.getId()
    }
}
