package com.rufree.dobi.common.repository

import com.rufree.dobi.common.entity.User
import com.rufree.dobi.common.entity.enums.SocialType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun findByEmailAndActive(email: String, active: Boolean = true): User?

    fun findByEmailAndSocialTypeAndActive(email: String, socialType: SocialType, active: Boolean = true): User?

    fun findByProviderIdAndSocialTypeAndActive(providerId: String, socialType: SocialType, active: Boolean = true): User?

    fun findByUsernameAndActive(username: String, active: Boolean = true): User?
}