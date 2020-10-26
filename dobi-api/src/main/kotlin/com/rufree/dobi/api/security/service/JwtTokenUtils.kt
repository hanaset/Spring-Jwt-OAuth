package com.rufree.dobi.api.security.service

import com.rufree.dobi.common.entity.enums.AuthorityName
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
        @Value("\${jw.expiration}") private val expiration: Long
) {


    fun getUsernameFromToken(token: String): String {
        return getClaimFromToken(token, Function { obj: Claims -> obj.subject })
    }

    fun getIssuedAtDateFromToken(token: String): Date {
        return getClaimFromToken(token, Function { obj: Claims -> obj.issuedAt })
    }

    fun getExpirationDateFromToken(token: String): Date {
        return getClaimFromToken(token, Function { obj: Claims -> obj.expiration })
    }

    fun getAudienceFromToken(token: String): String {
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

    fun generateToken(userDetails: UserDetails, authorities: List<AuthorityName>): String {
        val claims = mutableMapOf<String, Any>()
        claims["authority"] = authorities
        return doGenerateToken(claims, userDetails.username)
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
                .signWith(SignatureAlgorithm.ES512, secretKey)
                .compact()
    }

    private fun calculateExpirationDate(createdDate: Date): Date? {
        return Date(createdDate.time + expiration * 1000)
    }
}