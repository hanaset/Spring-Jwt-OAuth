package com.rufree.dobi.api.rest.controller

import com.rufree.dobi.api.config.web.dto.AuthenticatedUser
import com.rufree.dobi.api.rest.support.RestSupport
import com.rufree.dobi.api.service.UserInfoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["유저 정보 조회 및 수정"])
@RestController
@RequestMapping("/api/v1/user")
class UserInfoController(
    private val userInfoService: UserInfoService
) : RestSupport() {

    @GetMapping
    @ApiImplicitParams(
        ApiImplicitParam(
            name = "Authorization",
            value = "",
            required = true,
            allowEmptyValue = false,
            paramType = "header",
            dataTypeClass = String::class,
            example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdHkiOlt7ImF1dGhvcml0eSI6IkxFVkVMMCJ9XSwic3ViIjoia2FrYW8xNTE0NjYxOTk4IiwiYXVkIjoibW9iaWxlIiwiaWF0IjoxNjAzODY4OTg3LCJleHAiOjE2MDUzNDg5ODd9.wf8la-S_BP011E6ufCAC7eOp3nJghZ5RbuZ57GmN9vD3bkdxH2aCRSoff6FTHYZs6L9urRdXS64Z2R4kWppKhA"
        )
    )
    fun getUserInfo(user: AuthenticatedUser): ResponseEntity<Any> {
        userInfoService.getUserInfo(user)
        return response("ok")
    }
}