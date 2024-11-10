package team.yon2meet.backend.configuration.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import team.yon2meet.backend.domain.user.entity.User
import team.yon2meet.backend.domain.user.util.PasswordUtil
import team.yon2meet.backend.enums.LoginType

class CustomUserDetails(private val user: User) : UserDetails {
    fun getId() = user.id!!

    override fun getAuthorities(): Collection<GrantedAuthority> {
        val roles: MutableList<String> = ArrayList()
        roles.add("USER")

        return roles.map { SimpleGrantedAuthority(it) }
    }

    override fun getPassword(): String? {
        if (user.loginType == LoginType.KAKAO)
            return null

        val hashed = user.temporal?.hashedPassword ?: return null
        return PasswordUtil.bytesToHex(hashed)
    }

    override fun getUsername(): String = user.id.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
