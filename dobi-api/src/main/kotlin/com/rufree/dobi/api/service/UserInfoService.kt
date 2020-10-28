package com.rufree.dobi.api.service

import com.rufree.dobi.api.config.web.dto.AuthenticatedUser
import com.rufree.dobi.common.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserInfoService(
    private val userRepository: UserRepository
) {

    fun getUserInfo(user: AuthenticatedUser) {
        println(user)
    }
}