package com.rufree.dobi.api.config.web.dto

data class AuthenticatedUser(
    val uid: Long,
    val username: String,
    val token: String
)