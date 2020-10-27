package com.rufree.dobi.api.security

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationTokenFilter(
    private val userDetailsService: UserDetailsService,
    private val jwtTokenUtils: JwtTokenUtils,
    @Value("\${jwt.header}") private val header: String
) : OncePerRequestFilter() {

    private val logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter::class.java)

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        val requestHeader: String? = request.getHeader(this.header)

        if(requestHeader != null && requestHeader.startsWith("Bearer ")) {

            val authToken = requestHeader.substring(7)
            val username = jwtTokenUtils.getUsernameFromToken(authToken)

            if(username != null && SecurityContextHolder.getContext().authentication == null) {
                val userDetails = userDetailsService.loadUserByUsername(username)

                if(jwtTokenUtils.validateToken(authToken, userDetails)) {
                    val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
        }

        filterChain.doFilter(request, response)
    }

}