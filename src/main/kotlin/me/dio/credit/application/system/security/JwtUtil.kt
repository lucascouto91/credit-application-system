package me.dio.credit.application.system.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import me.dio.credit.application.system.exception.AuthenticationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtUtil {

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secrete: String? = null

    private fun getKey(): Key {
        return Keys.hmacShaKeyFor(secrete!!.toByteArray())
    }

    fun generateToken(id: Long): String {
        return Jwts.builder()
            .subject(id.toString())
            .expiration(Date(System.currentTimeMillis() + expiration!!))
            .signWith(getKey())
            .compact()
    }


    fun isValidToken(token: String): Boolean {
        val claims = getClaims(token)
        return !(claims.subject == null || claims.expiration == null || Date().after(claims.expiration))
    }


    private fun getClaims(token: String): Claims {
        val key = Keys.hmacShaKeyFor(secrete!!.toByteArray())
        try {
            return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload
        } catch (ex: Exception) {
            throw AuthenticationException("Token Inválido", "999")
        }
    }

    fun getSubject(token: String): String {
        return getClaims(token).subject
    }

}