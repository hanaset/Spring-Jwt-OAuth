package com.rufree.dobi.api.security.service

import com.rufree.dobi.api.security.JwtUser
import com.rufree.dobi.common.entity.Authority
import com.rufree.dobi.common.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class JwtFactory {

    companion object {
        fun create(user: User): JwtUser {
            return JwtUser(
                    uid = user.id,
                    email = user.email,
                    authorities = mapToGrantedAuthorities(user.authorities)
            )
        }

        private fun mapToGrantedAuthorities(authorities: List<Authority>): MutableList<GrantedAuthority> {
            return authorities.map { SimpleGrantedAuthority(it.authorityName.name) }.toMutableList()
        }
    }
}