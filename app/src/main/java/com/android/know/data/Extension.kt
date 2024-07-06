package com.android.know.data

import retrofit2.Response

fun <T> requestBody(request: Response<T>): Result<T> {
    return try {
        if (request.isSuccessful) {
            request.body()?.let {
                Result.success(it)
            } ?: Result.failure(Throwable(request.message()))
        } else {
            val message = request.errorBody()?.string()
            Result.failure(Throwable(message))
        }
    } catch (ex: IllegalArgumentException) {
        Result.failure(ex)
    }
}