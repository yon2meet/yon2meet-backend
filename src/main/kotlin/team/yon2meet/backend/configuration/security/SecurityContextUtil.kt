package team.yon2meet.backend.configuration.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

object SecurityContextUtil {
    fun getAuthentication(): UsernamePasswordAuthenticationToken =
        SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken
}
