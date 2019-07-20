package io.freshdroid.skyscanner.core.network.interceptors

import io.freshdroid.skyscanner.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.*
import javax.inject.Inject

internal const val ANDROID_DEVICE_TYPE_STRING = "android"

internal class ApiRequestInterceptor @Inject constructor(
    private val _locale: Locale
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(request(chain.request()))
    }

    private fun request(initialRequest: Request): Request {
        var requestBuilder = initialRequest.newBuilder()
            .header("User-Agent", ANDROID_DEVICE_TYPE_STRING)
            .header("X-Agent-Version", BuildConfig.VERSION_CODE.toString())
            .header("Accept", "application/json")
            .header("Accept-Language", _locale.language)

        return requestBuilder.build()
    }

}