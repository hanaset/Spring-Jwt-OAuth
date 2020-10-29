package com.rufree.dobi.api.rest.dto.response

import com.rufree.dobi.common.entity.User
import com.rufree.dobi.common.entity.enums.Sex
import com.rufree.dobi.common.entity.enums.SocialType

data class UserInfoResponse(
        val uid: Long,
        val name: String,
        val nickname: String,
        val phone: String,
        val email: String,
        val sex: Sex,
        val socialType: SocialType
) {
    companion object {
        fun of(user: User): UserInfoResponse {
            return UserInfoResponse(
                    uid = user.id,
                    name = user.username,
                    nickname = user.nickname,
                    phone = user.phone ?: "",
                    email = user.email,
                    sex = user.sex ?: Sex.UNKNOWN,
                    socialType = user.socialType
            )
        }
    }
}