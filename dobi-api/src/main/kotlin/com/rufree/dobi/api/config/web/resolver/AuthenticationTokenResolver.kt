package com.rufree.dobi.api.config.web.resolver

import com.rufree.dobi.api.config.web.dto.AuthenticatedUser
import com.rufree.dobi.api.security.JwtTokenUtils
import com.rufree.dobi.common.repository.UserRepository
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class AuthenticationTokenResolver(
    private val header: String,
    private val jwtTokenUtils: JwtTokenUtils,
    private val userRepository: UserRepository
): HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == AuthenticatedUser::class.java
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        val token = webRequest.getToken()
        val username = jwtTokenUtils.getUsernameFromToken(token) ?: return null
        val user = userRepository.findByUsernameAndActive(username) ?: return null

        return AuthenticatedUser(uid = user.id, username = user.username, token = token)
    }

    private fun NativeWebRequest.getToken(): String {
        return getHeader(header)?.substring(7) ?: ""
    }
}