package com.rufree.dobi.common.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_authority")
class UserAuthority(
        @Id
        val uid: Long,

        @Column(name = "authority_id")
        var authorityId: Long
) : AbstractBaseAuditEntity()