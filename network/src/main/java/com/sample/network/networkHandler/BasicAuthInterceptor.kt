package com.sample.network.networkHandler

import com.sample.network.utils.CAT_API_KEY
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class BasicAuthInterceptor :
    Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val authenticatedRequest: Request = request.newBuilder()
            .apply {
                if (request.header("Content-Type") == null)
                    header("Content-Type", "application/json")
                if (request.header("api-Type") == null)
                    header("x-api-key", CAT_API_KEY)
            }.build()
        return chain.proceed(authenticatedRequest)
    }

}