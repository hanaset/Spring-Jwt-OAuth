package com.rufree.dobi.api.utils

class KakaoAccountUtils {

    companion object {

        fun getUsernameByKakaoAccount(providerId: Long): String {
            return ProviderConstants.KAKAO + providerId
        }

        fun getPasswordByKakaoAccount(providerId: Long): String {
            return ProviderConstants.KAKAO + providerId
        }
    }
}