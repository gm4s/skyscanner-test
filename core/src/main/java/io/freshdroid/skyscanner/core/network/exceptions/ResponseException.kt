package io.freshdroid.skyscanner.core.network.exceptions

import retrofit2.Response

open class ResponseException(
    private val _response: Response<*>
) : RuntimeException() {

    fun response(): Response<*> = _response

}