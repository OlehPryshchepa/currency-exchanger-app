package com.example.currency_changer_app.util

import java.security.MessageDigest
import java.security.SecureRandom

class UtilClass() {

    companion object {
        private const val SALT_LENGTH = 16

        fun isValidEmail(email: String): Boolean {
            return email.matches(EMAIL_PATTERN)
        }

        fun hashPassword(password: String, salt: String): String {
            val bytes = (password + salt).toByteArray()
            val digest = MessageDigest.getInstance("SHA-256")
            val hashBytes = digest.digest(bytes)
            return hashBytes.joinToString("") { "%02x".format(it) }
        }

        fun generateSalt(): String {
            val random = SecureRandom()
            val salt = ByteArray(SALT_LENGTH)
            random.nextBytes(salt)
            return salt.toString()
        }
    }
}