package com.rufree.dobi.api.security

import com.google.gson.Gson
import com.rufree.dobi.api.rest.support.RestSupport
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint: RestSupport(), AuthenticationEntryPoint {

    private val logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter::class.java)

    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException) {

        val result = unauthorized(authException.message ?: "")
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.writer.write(Gson().toJson(result.body))

        logger.error("Exception: $authException")
    }
}