package com.rich.joint.common.entity

import com.rich.joint.common.entity.enums.SocialType
import com.rich.joint.common.entity.enums.Role
import com.rich.joint.common.entity.enums.Sex
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

    @Enumerated(value = EnumType.STRING)
    val role: Role = Role.LEVEL1,

    val name: String,

    val email: String,

    val phone: String? = null,

    @Enumerated(value = EnumType.STRING)
    val sex: Sex? = null,

    val birthday: String? = null,

    @Column(name = "last_login")
    var lastLogin: ZonedDateTime = ZonedDateTime.now(),

    var active: Boolean = true

): AbstractBaseAuditEntity()