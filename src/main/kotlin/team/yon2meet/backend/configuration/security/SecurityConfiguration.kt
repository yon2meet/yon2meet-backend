package team.yon2meet.backend.configuration.security

import jakarta.servlet.DispatcherType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
class SecurityConfiguration(
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler,
    private val jwtRequestFilter: JwtTokenFilter,
) {
    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf { it.disable() }
            .cors {}
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .headers {
                it.frameOptions { frameOptionsConfig -> frameOptionsConfig.sameOrigin() }
            }
            .sessionManagement {
                it.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
            .exceptionHandling { exceptionHandling: ExceptionHandlingConfigurer<HttpSecurity?> ->
                exceptionHandling
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .accessDeniedHandler(jwtAccessDeniedHandler)
            }
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authorizeHttpRequests { authorize ->
                authorize
                    .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                    .requestMatchers("/auth/login/kakao").permitAll()
                    .requestMatchers("/auth/login/temporal").permitAll()
                    .requestMatchers("/swagger-ui/**").permitAll()
                    .requestMatchers("/v3/api-docs/**").permitAll()
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/actuator/health").permitAll()
                    .anyRequest().authenticated()
            }
            .build()
    }
}
