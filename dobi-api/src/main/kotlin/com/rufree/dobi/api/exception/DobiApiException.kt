package com.rich.joint.api.exception

import com.rufree.dobi.api.exception.ErrorCode

open class DobiApiException(
    val code: ErrorCode,
    override val message: String? = null
) : RuntimeException(
        message ?: code.message
)
