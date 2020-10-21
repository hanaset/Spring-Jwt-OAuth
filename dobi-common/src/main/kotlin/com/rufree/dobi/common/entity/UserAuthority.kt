package com.rufree.dobi.common.entity

import com.rufree.dobi.common.entity.enums.Role
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_authority")
class UserAuthority(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    val uid: Long,

    @Enumerated(value = EnumType.STRING)
    val role: Role
): AbstractBaseAuditEntity()