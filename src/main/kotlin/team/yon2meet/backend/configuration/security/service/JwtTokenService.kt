package team.yon2meet.backend.configuration.security.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ClaimsBuilder
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.ZonedDateTime
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtTokenService(
    @Value("\${jwt.secret-key}") secretKey: String,
    @param:Value("\${jwt.token-validity-in-seconds}") private val accessTokenValiditySeconds: Long,
) {
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))

    fun createToken(userId: Long): String {
        val claims: ClaimsBuilder = Jwts.claims()
        claims.add("userId", userId)

        val now: ZonedDateTime = ZonedDateTime.now()
        val tokenValidity: ZonedDateTime = now.plusSeconds(accessTokenValiditySeconds)

        return Jwts.builder()
            .claims(claims.build())
            .issuedAt(Date.from(now.toInstant()))
            .expiration(Date.from(tokenValidity.toInstant()))
            .signWith(secretKey)
//            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getUserId(token: String): Long {
        val userId = getClaims(token)
            .payload
            .get("userId", Long::class.javaObjectType)
        return userId
    }

    fun isExpired(token: String): Boolean {
        try {
            val claims: Jws<Claims> = getClaims(token)
            val expiredDate: Date = claims
                .payload
                .expiration
            val now = Date()
            return now.after(expiredDate)
        } catch (e: ExpiredJwtException) {
            throw BadCredentialsException("JWT accessToken has expired", e)
        } catch (e: SecurityException) {
            throw BadCredentialsException("Error validating JWT accessToken", e)
        } catch (e: MalformedJwtException) {
            throw BadCredentialsException("Error validating JWT accessToken", e)
        } catch (e: UnsupportedJwtException) {
            throw BadCredentialsException("Error validating JWT accessToken", e)
        } catch (e: IllegalArgumentException) {
            throw BadCredentialsException("Error validating JWT accessToken", e)
        }
    }

    private fun getClaims(token: String): Jws<Claims> {
        return Jwts.parser()
            .keyLocator { secretKey }
//            .setSigningKey(secretKey)
            .build()
            .parseSignedClaims(token)
//            .parseClaimsJws(token)
    }
}
