package com.sample.network.networkHandler

import android.util.Log

suspend fun <T : Any> ApiResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): ApiResult<T> = apply {
    if (this is ApiSuccess)
        executable(data)
}

suspend fun <T : Any> ApiResult<T>.onError(
    executable: suspend (code: Int, message: String?) -> Unit
): ApiResult<T> = apply {
    if (this is ApiError<T>) {
        Log.d("API_ERROR", "$code : $message")
        executable(code, message)
    }
}

suspend fun <T : Any> ApiResult<T>.onException(
    executable: suspend (e: Exception) -> Unit
): ApiResult<T> = apply {
    if (this is ApiException<T>) {
        Log.d("API_EXCEPTION", "${e.message}")
        executable(e)
    }
}