package com.rufree.dobi.common.repository

import com.rufree.dobi.common.entity.Authority
import com.rufree.dobi.common.entity.enums.AuthorityName
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository: JpaRepository<Authority, Long> {

    fun findByAuthorityName(authorityName: AuthorityName): Authority
}