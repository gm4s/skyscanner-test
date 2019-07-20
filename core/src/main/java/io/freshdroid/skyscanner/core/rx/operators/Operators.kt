package io.freshdroid.skyscanner.core.rx.operators

import com.squareup.moshi.Moshi
import io.freshdroid.skyscanner.core.rx.operators.ApiErrorOperator

object Operators {

    @JvmStatic
    fun apiError(moshi: Moshi): ApiErrorOperator = ApiErrorOperator(moshi)

}