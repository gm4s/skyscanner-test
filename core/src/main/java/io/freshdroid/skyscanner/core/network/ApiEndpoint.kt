package io.freshdroid.skyscanner.core.network

import io.freshdroid.skyscanner.core.BuildConfig

enum class ApiEndpoint(
    val url: String
) {
    SKYSCANNER(BuildConfig.BASE_URL)
}