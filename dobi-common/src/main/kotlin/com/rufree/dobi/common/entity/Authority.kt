package com.rufree.dobi.common.entity

import com.rufree.dobi.common.entity.enums.AuthorityName
import javax.persistence.*

@Entity
@Table(name = "authority")
class Authority(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,

        @Enumerated(EnumType.STRING)
        @Column(name = "name")
        val authorityName: AuthorityName,

        @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
        var user: List<User> = mutableListOf()
): AbstractBaseAuditEntity()