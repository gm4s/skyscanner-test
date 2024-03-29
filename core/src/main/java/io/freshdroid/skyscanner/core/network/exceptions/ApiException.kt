package io.freshdroid.skyscanner.core.network.exceptions

import io.freshdroid.skyscanner.core.network.ErrorEnvelope
import retrofit2.Response

open class ApiException(
    private val _errorEnvelope: ErrorEnvelope,
    response: Response<*>
) : ResponseException(response) {

    fun errorEnvelope(): ErrorEnvelope = _errorEnvelope

}