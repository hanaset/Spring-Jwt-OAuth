package com.rufree.dobi.api.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Function

@Component
class JwtTokenUtils(
    @Value("\${jwt.secret}") private val secretKey: String,
    @Value("\${jwt.expiration}") private val expiration: Long
) {

    companion object {
        const val AUDIENCE_MOBILE = "mobile"
    }

    fun getUsernameFromToken(token: String): String? {
        return getClaimFromToken(token, Function { obj: Claims -> obj.subject })
    }

    fun getIssuedAtDateFromToken(token: String): Date {
        return getClaimFromToken(token, Function { obj: Claims -> obj.issuedAt })
    }

    fun getExpirationDateFromToken(token: String): Date {
        return getClaimFromToken(token, Function { obj: Claims -> obj.expiration })
    }

    fun getAudienceFromToken(token: String): String? {
        return getClaimFromToken(token, Function { obj: Claims -> obj.audience })
    }

    fun <T> getClaimFromToken(token: String, claimsResolver: Function<Claims, T>): T {
        val claims: Claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }

    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .body
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    private fun generateAudience(): String { return AUDIENCE_MOBILE }


    private fun ignoreTokenExpiration(token: String): Boolean {
        val audience = getAudienceFromToken(token)
        return AUDIENCE_MOBILE == audience
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims = mutableMapOf<String, Any>()
        claims["authority"] = userDetails.authorities
        return doGenerateToken(claims, userDetails.username, generateAudience())
    }

    private fun doGenerateToken(claims: Map<String, Any>, subject: String, audience: String): String {
        val now = Date()
        val expirationDate = calculateExpirationDate(now)

        println("doGenerateToken $now")

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setAudience(audience)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val user = userDetails as JwtUser
        val username = getUsernameFromToken(token)
        val created = getIssuedAtDateFromToken(token)
        return username == user.username && !isTokenExpired(token)
    }

    private fun calculateExpirationDate(createdDate: Date): Date? {
        return Date(createdDate.time + expiration * 1000)
    }
}