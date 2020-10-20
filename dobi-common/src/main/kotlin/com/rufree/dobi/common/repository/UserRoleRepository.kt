package com.rufree.dobi.common.repository

import com.rufree.dobi.common.entity.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRoleRepository: JpaRepository<UserRole, Long> {
    fun findByUid(uid: Long): List<UserRole>
    fun findByUidAndRole(uid: Long, role: UserRole): UserRole?
}