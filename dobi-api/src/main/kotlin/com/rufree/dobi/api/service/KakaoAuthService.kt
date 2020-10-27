package com.rufree.dobi.api.service

import com.rufree.dobi.api.client.kakao.KakaoAuthClient
import com.rufree.dobi.api.client.kakao.KakaoClient
import com.rufree.dobi.api.exception.DobiApiException
import com.rufree.dobi.api.exception.ErrorCode
import com.rufree.dobi.common.entity.enums.SocialType
import com.rufree.dobi.common.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class KakaoAuthService(
    private val kakaoAuthClient: KakaoAuthClient,
    private val kakaoClient: KakaoClient,
    private val userAuthService: UserAuthService,
    private val userRepository: UserRepository
) {

    fun kakaoAuth(code: String, clientId: String, redirectUrl: String) {
        val tokenResult = kakaoAuthClient.token(redirectUrl, code)

        if(!tokenResult.isSuccessful) throw DobiApiException(ErrorCode.UNAUTHORIZED_KAKAO, "카카오 토큰 조회 에러")

        val res = kakaoClient.getUserInfo(tokenResult.body()!!.accessToken)

        if(!res.isSuccessful) throw DobiApiException(ErrorCode.UNAUTHORIZED_KAKAO, "카카오 유저 정보 조회 에러")

        val kakaoRes = res.body()!!
        // 회원가입 여부 확인 후, null일 경우 회원가입
        val user = userRepository.findByEmailAndSocialTypeAndActive(email = kakaoRes.kakaoAccount.email, socialType = SocialType.KAKAO) ?: userAuthService.kakaoJoin(kakaoRes)


    }

}