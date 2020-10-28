package com.rufree.dobi.api.rest

import com.rufree.dobi.api.rest.dto.response.HealthCheckResponse
import com.rufree.dobi.api.rest.support.RestSupport
import org.springframework.boot.info.BuildProperties
import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime

@RestController
class HealthCheckController(
        private val buildProperties: BuildProperties,
        private val environment: Environment
) : RestSupport() {

    @GetMapping("/", "/health")
    fun healthCheck(): ResponseEntity<*> {
        return response(
            HealthCheckResponse(
                application = buildProperties.name,
                profiles = environment.activeProfiles.toList(),
                health = "OK",
                time = ZonedDateTime.now()
            )
        )
    }
}