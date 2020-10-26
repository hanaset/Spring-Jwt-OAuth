package com.rufree.dobi.api.security.service

import com.rufree.dobi.api.exception.DobiApiNotFoundUserException
import com.rufree.dobi.common.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service(value = "userDetailsService")
class JwtUserDetailsService(
        private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {

        val user = userRepository.findByEmailAndActive(username ?: throw DobiApiNotFoundUserException())
                ?: throw DobiApiNotFoundUserException()

        return JwtFactory.create(user)
    }
}