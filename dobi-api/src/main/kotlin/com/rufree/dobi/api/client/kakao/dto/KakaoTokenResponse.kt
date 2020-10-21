package com.rufree.dobi.api.client.kakao.dto

class KakaoTokenResponse(
        val tokenType: String,
        val accessToken: String,
        val expiresIn: Long,
        val refreshToken: String,
        val refreshTokenExpiresIn: Long,
        val scope: String
)