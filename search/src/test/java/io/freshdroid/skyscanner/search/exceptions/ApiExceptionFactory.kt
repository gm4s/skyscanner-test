package io.freshdroid.skyscanner.search.exceptions

import io.freshdroid.skyscanner.core.network.ErrorEnvelope
import io.freshdroid.skyscanner.core.network.exceptions.ApiException
import okhttp3.ResponseBody
import retrofit2.Response

internal object ApiExceptionFactory {

    fun badRequestException(): ApiException {
        val envelope = ErrorEnvelope(
            "bad request",
            400
        )
        val body = ResponseBody.create(null, "")
        val response = Response.error<ResponseBody>(400, body)

        return ApiException(envelope, response)
    }

}