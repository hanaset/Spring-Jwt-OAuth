package com.rufree.dobi.api.service

import com.rufree.dobi.api.client.kakao.KakaoAuthClient
import com.rufree.dobi.api.client.kakao.KakaoClient
import com.rufree.dobi.api.exception.DobiApiException
import com.rufree.dobi.api.exception.ErrorCode
import com.rufree.dobi.api.rest.dto.request.SigninRequest
import com.rufree.dobi.api.rest.dto.response.SignInResponse
import com.rufree.dobi.api.security.JwtTokenUtils
import com.rufree.dobi.api.utils.KakaoAccountUtils
import com.rufree.dobi.common.entity.enums.SocialType
import com.rufree.dobi.common.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class KakaoAuthService(
    private val kakaoAuthClient: KakaoAuthClient,
    private val kakaoClient: KakaoClient,
    private val userAuthService: UserAuthService,
    private val userRepository: UserRepository,
    private val userDetailsService: UserDetailsService,
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenUtils: JwtTokenUtils
) {

    fun kakaoSignIn(request: SigninRequest): SignInResponse {
        val tokenResult = kakaoAuthClient.token(redirectUri = request.redirectUri, code = request.code)

        if (!tokenResult.isSuccessful) throw DobiApiException(ErrorCode.UNAUTHORIZED_KAKAO, "카카오 토큰 조회 에러")

        val res = kakaoClient.getUserInfo(tokenResult.body()!!.accessToken)

        if (!res.isSuccessful) throw DobiApiException(ErrorCode.UNAUTHORIZED_KAKAO, "카카오 유저 정보 조회 에러")
        val kakaoRes = res.body()!!
        // 회원가입 여부 확인 후, null일 경우 회원가입
        val user = userRepository.findByProviderIdAndSocialTypeAndActive(providerId = kakaoRes.id.toString(), socialType = SocialType.KAKAO)
                ?: userAuthService.kakaoJoin(kakaoRes)

        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(KakaoAccountUtils.getUsernameByKakaoAccount(kakaoRes.id), KakaoAccountUtils.getPasswordByKakaoAccount(kakaoRes.id)))
        val userDetails = userDetailsService.loadUserByUsername(user.username)
        SecurityContextHolder.getContext().authentication = authentication

        return SignInResponse(
            nickname = user.nickname,
            email = user.email,
            socialType = user.socialType,
            token = jwtTokenUtils.generateToken(userDetails)
        )
    }

}