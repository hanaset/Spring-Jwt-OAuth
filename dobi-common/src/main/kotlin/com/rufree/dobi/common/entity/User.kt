package com.rufree.dobi.common.entity

import com.rufree.dobi.common.entity.enums.Sex
import com.rufree.dobi.common.entity.enums.SocialType
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @Column(name = "social_type")
    @Enumerated(value = EnumType.STRING)
    val socialType: SocialType,

    val name: String,

    var nickname: String,

    val email: String,

    var phone: String,

    @Enumerated(value = EnumType.STRING)
    var sex: Sex,

    var birthday: String,

    var profile: String = "",

    @Column(name = "last_login")
    var lastLogin: ZonedDateTime = ZonedDateTime.now(),

    var active: Boolean = true,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = [JoinColumn(name = "uid", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "authority_id", referencedColumnName = "id")]
    )
    var authorities: List<Authority> = mutableListOf()

) : AbstractBaseAuditEntity()