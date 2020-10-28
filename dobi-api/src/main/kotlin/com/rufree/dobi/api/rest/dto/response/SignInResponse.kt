package com.rufree.dobi.api.rest.dto.response

import com.rufree.dobi.common.entity.enums.SocialType

class SignInResponse(
    val nickname: String,
    val email: String,
    val socialType: SocialType,
    val token: String
)