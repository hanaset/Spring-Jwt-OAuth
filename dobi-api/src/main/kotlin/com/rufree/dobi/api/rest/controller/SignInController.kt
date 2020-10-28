package com.rufree.dobi.api.rest.controller

import com.rufree.dobi.api.rest.dto.request.SigninRequest
import com.rufree.dobi.api.rest.support.RestSupport
import com.rufree.dobi.api.service.KakaoAuthService
import io.swagger.annotations.Api
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["회원가입 및 로그인"])
@RestController
@RequestMapping("/api/v1/auth")
class SignInController(
    private val kakaoAuthService: KakaoAuthService
): RestSupport() {

    @PostMapping("/kakao/signin")
    fun kakaoSignIn(@RequestBody request: SigninRequest): ResponseEntity<Any> {
        return response(kakaoAuthService.kakaoSignIn(request))
    }
}