package com.rufree.dobi.api.client.kakao.dto.response

import com.google.gson.annotations.SerializedName


data class KakaoTokenResponse(
    @SerializedName("token_type")
    val tokenType: String,

    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("expires_in")
    val expiresIn: Long,

    @SerializedName("refresh_token")
    val refreshToken: String,

    @SerializedName("refresh_token_expires_in")
    val refreshTokenExpiresIn: Long,
    val scope: String
)