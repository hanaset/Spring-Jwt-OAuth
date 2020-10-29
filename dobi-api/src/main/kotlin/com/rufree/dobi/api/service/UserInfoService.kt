package com.rufree.dobi.api.service

import com.rufree.dobi.api.config.web.dto.AuthenticatedUser
import com.rufree.dobi.api.exception.DobiApiNotFoundUserException
import com.rufree.dobi.api.rest.dto.response.UserInfoResponse
import com.rufree.dobi.common.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserInfoService(
    private val userRepository: UserRepository
) {

    fun getUserInfo(user: AuthenticatedUser): UserInfoResponse {
        val user = userRepository.findById(user.uid).orElseThrow { DobiApiNotFoundUserException() }
        return UserInfoResponse.of(user)
    }
}