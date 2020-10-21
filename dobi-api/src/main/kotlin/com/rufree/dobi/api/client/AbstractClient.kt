package com.rufree.dobi.api.client

import org.slf4j.Logger
import retrofit2.Call
import retrofit2.Response

open class AbstractClient {

    protected fun <T> isSuccessful(res: Call<T>, funName: String, logger: Logger): Response<T> {
        val exc = res.execute()
        if(!exc.isSuccessful)
            logger.error("[Failed!!] ${funName} ==> ${exc.errorBody()!!.string()}")

        return exc
    }
}