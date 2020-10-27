package com.rufree.dobi.api.client.kakao

import com.rufree.dobi.api.client.kakao.dto.response.KakaoTokenResponse
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface KakaoAuthClientService {

    @POST("/oauth/token")
    @Headers("content-type: application/x-www-form-urlencoded;charset=utf-8")
    fun token(@Query("grant_type") grantType: String = "authorization_code",
              @Query("client_id") clientId: String,
              @Query("redirect_uri") redirectUri: String,
              @Query("code") code: String,
              @Query("client_secret") clientSecret: String?
    ): Call<KakaoTokenResponse>
}