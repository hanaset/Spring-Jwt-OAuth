package com.rufree.dobi.common.repository

import com.rufree.dobi.common.entity.UserAuthority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAuthorityRepository: JpaRepository<UserAuthority, Long> {

    fun findByUid(uid: Long): UserAuthority?
}