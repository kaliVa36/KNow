package com.android.know

import com.android.know.data.API_KEY_HEADER
import okhttp3.Interceptor
import okhttp3.Response

class KNowInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder().header(API_KEY_HEADER, BuildConfig.API_KEY)
        return chain.proceed(newRequest.build())
    }
}
