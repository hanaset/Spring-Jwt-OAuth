package com.rufree.dobi.api.client.kakao

import com.rufree.dobi.api.client.AbstractClient
import com.rufree.dobi.api.client.kakao.dto.response.KakaoTokenResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Component
class KakaoAuthClient(
        @Value("\${kakao.url.auth}") private val kakaoUrl: String,
        @Value("\${kakao.client_id}") private val clientId: String,
        @Value("\${kakao.client_secret}") private val clientSecret: String
) : AbstractClient() {
    private val logger = LoggerFactory.getLogger(this::class.java)

    private lateinit var kakaoAuthClientService: KakaoAuthClientService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(kakaoUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        this.kakaoAuthClientService = retrofit.create(KakaoAuthClientService::class.java)

        logger.info("KakaoAuthClientService Init::url=${kakaoUrl}")
    }

    fun token(redirectUri: String, code: String): Response<KakaoTokenResponse> {
        return isSuccessful(kakaoAuthClientService.token(clientId = clientId, redirectUri = redirectUri, code = code, clientSecret = clientSecret), "KakaoAuthClientService::token", logger)
    }
}