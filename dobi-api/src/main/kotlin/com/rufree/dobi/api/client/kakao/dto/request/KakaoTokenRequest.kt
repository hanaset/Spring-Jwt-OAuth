package com.rufree.dobi.api.client.kakao.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class KakaoTokenRequest(
    val grantType: String = "authorization_code",
    val clientId: String,
    val redirectUri: String,
    val code: String,
    val clientSecret: String
)