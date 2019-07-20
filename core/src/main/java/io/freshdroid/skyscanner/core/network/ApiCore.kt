package io.freshdroid.skyscanner.core.network

import com.squareup.moshi.Moshi
import io.freshdroid.skyscanner.core.rx.operators.ApiErrorOperator
import io.freshdroid.skyscanner.core.rx.operators.Operators

open class ApiCore(
    private val _moshi: Moshi
) {

    fun apiErrorOperator(): ApiErrorOperator {
        return Operators.apiError(_moshi)
    }

}