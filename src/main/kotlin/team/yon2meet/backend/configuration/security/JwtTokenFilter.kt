package team.yon2meet.backend.configuration.security

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import team.yon2meet.backend.configuration.security.service.CustomUserDetailsService
import team.yon2meet.backend.configuration.security.service.JwtTokenService

@Component
class JwtTokenFilter(
    private val customUserDetailsService: CustomUserDetailsService,
    private val jwtTokenService: JwtTokenService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: jakarta.servlet.http.HttpServletRequest,
        response: jakarta.servlet.http.HttpServletResponse,
        filterChain: jakarta.servlet.FilterChain,
    ) {
        val authorizationHeader = request.getHeader("Authorization")

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            val token = authorizationHeader.substring(7)

            if (jwtTokenService.isExpired(token)) throw BadCredentialsException("만료된 토큰입니다.")

            val userId = jwtTokenService.getUserId(token)
            val userDetails: UserDetails = customUserDetailsService.loadUserByUsername(userId.toString())

            val usernamePasswordAuthenticationToken =
                UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)

            SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
        }
        filterChain.doFilter(request, response)
    }
}
