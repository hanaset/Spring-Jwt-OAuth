package com.rufree.dobi.api.rest.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class SigninRequest(
    val code: String,
    val redirectUri: String,
    val agreementMarketingRecv: Boolean
)