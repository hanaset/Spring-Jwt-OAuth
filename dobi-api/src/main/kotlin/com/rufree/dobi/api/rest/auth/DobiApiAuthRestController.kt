package com.rufree.dobi.api.rest.auth

import com.rich.joint.api.rest.support.RestSupport
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api/v1/auth")
class DobiApiAuthRestController(

) : RestSupport() {

    @PostMapping("/login")
    fun login(): ResponseEntity<*> {
        return response("")
    }
}