package com.rufree.dobi.api.security

import com.rufree.dobi.common.entity.Authority
import com.rufree.dobi.common.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class JwtUserFactory {

    companion object {
        fun create(user: User): JwtUser {
            return JwtUser(
                uid = user.id,
                nickname = user.nickname,
                username = user.username,
                socialType = user.socialType,
                password = user.password,
                email = user.email,
                authorities = mapToGrantedAuthorities(user.authorities)
            )
        }

        private fun mapToGrantedAuthorities(authorities: List<Authority>): MutableList<GrantedAuthority> {
            return authorities.map { SimpleGrantedAuthority(it.authorityName.name) }.toMutableList()
        }
    }
}