package team.yon2meet.backend.configuration.security.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.yon2meet.backend.configuration.security.CustomUserDetails
import team.yon2meet.backend.domain.user.entity.User
import team.yon2meet.backend.domain.user.repository.UserRepository

@Service
@Transactional(readOnly = true)
class CustomUserDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(userId: String): UserDetails {
        val user: User = userRepository.findById(userId.toLong())
            .orElseThrow { UsernameNotFoundException("해당하는 유저가 없습니다.") }

        return CustomUserDetails(user)
    }
}
