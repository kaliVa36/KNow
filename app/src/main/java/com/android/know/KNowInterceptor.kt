package com.android.know

import com.android.know.data.API_KEY_HEADER
import okhttp3.Interceptor
import okhttp3.Response

class KNowInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder().header(API_KEY_HEADER, "d5088a254f7f4d0ba2021ac33098866b")
        return chain.proceed(newRequest.build())
    }
}
