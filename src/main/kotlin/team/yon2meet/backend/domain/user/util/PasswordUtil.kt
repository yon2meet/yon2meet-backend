package team.yon2meet.backend.domain.user.util

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

object PasswordUtil {
    private val digest = MessageDigest.getInstance("SHA-256")!!

    fun hashPassword(password: String): ByteArray {
        return digest.digest(password.toByteArray(StandardCharsets.UTF_8))
    }

    fun isPasswordMatch(password: String, hashedPassword: ByteArray): Boolean {
        return digest.digest(password.toByteArray(StandardCharsets.UTF_8)).contentEquals(hashedPassword)
    }

    fun bytesToHex(bytes: ByteArray): String {
        val builder = StringBuilder()
        for (b in bytes) {
            builder.append(String.format("%02x", b))
        }
        return builder.toString()
    }
}
