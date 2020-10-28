package com.rufree.dobi.api.config.web.resolver

import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class AuthenticationTokenResolver(
    private val header: String
): HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return true
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory): Any {
        return webRequest.getToken()
    }

    private fun NativeWebRequest.getToken(): String {
        return getHeader(header) ?: ""
    }
}