package com.rufree.dobi.api.client.kakao

import com.rufree.dobi.api.client.kakao.dto.response.KakaoUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface KakaoClientService {

    @GET("/v2/user/me")
    @Headers("content-type: application/x-www-form-urlencoded;charset=utf-8")
    fun getUserInfo(@Header("Authorization") token: String): Call<KakaoUserResponse>
}