package com.rich.joint.api.rest.dto.response

import com.rich.joint.common.toSeoulEpochSecond
import java.time.LocalDateTime
import java.time.ZonedDateTime

data class HealthCheckResponse(
        var application: String,
        var profiles: List<String>,
        var health: String,
        var time: ZonedDateTime
)

data class Response<T>(
        val data: T,
        val timestamp: Long = LocalDateTime.now().toSeoulEpochSecond()!!,
        val message: String
)

