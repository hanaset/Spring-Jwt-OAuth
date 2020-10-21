package com.rufree.dobi.api.rest.controller.signin

import com.rich.joint.api.rest.support.RestSupport
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.net.URI
import javax.servlet.http.HttpServletRequest

@Api(tags = ["카카오 백엔드 회원가입"])
@Controller
class KakaoBackendSigninController(
        @Value("\${kakao.client_id}") private val clientId: String
) : RestSupport() {


    @GetMapping("/api/v1/kakao/backend/signin")
    fun kakaoBackendSignPage(): ResponseEntity<*> {
        val redirectUrl = "https://kauth.kakao.com/oauth/authorize?client_id=${clientId}&redirect_uri=http://localhost:5000/api/v1/kakao/backend/signin/callback&response_type=code"
        val uri = URI(redirectUrl)
        val headers = HttpHeaders()
        headers.location = uri
        return ResponseEntity<Any>(headers, HttpStatus.SEE_OTHER)
    }


    @GetMapping("/api/v1/kakao/backend/signin/callback")
    fun kakaoBackendSigninCallback(request: HttpServletRequest, @RequestParam("code") code: String): ResponseEntity<*> {
        return response("")
    }
}