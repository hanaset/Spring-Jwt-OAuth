package com.rufree.dobi.common.entity

import com.rufree.dobi.common.entity.enums.AuthorityName
import javax.persistence.*

@Entity
@Table(name = "user_authority")
class UserAuthority(
        @Id
        val uid: Long,

        @Column(name = "authority_id")
        var authorityId: Long
) : AbstractBaseAuditEntity()