package com.rufree.dobi.common.entity

import com.rufree.dobi.common.entity.enums.Sex
import com.rufree.dobi.common.entity.enums.SocialType
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

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

    var active: Boolean = true

) : AbstractBaseAuditEntity()