package io.freshdroid.skyscanner.search.viewmodels.errors

import io.freshdroid.skyscanner.core.network.ErrorEnvelope
import io.reactivex.Observable

interface SearchViewModelErrors {

    fun fetchItinerariesApiError(): Observable<ErrorEnvelope>

}