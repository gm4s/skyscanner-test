package io.freshdroid.skyscanner.search.network

import com.squareup.moshi.Moshi
import io.freshdroid.skyscanner.core.network.*
import io.freshdroid.skyscanner.models.Itinerary
import io.freshdroid.skyscanner.search.network.adapters.SearchAdapter
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class ApiSearch(
    private val _httpTransitionFactory: HttpTransitionFactoryType,
    private val _moshi: Moshi
) : ApiCore(_moshi), ApiSearchType {

    override fun fetchItineraries(): Observable<ArrayList<Itinerary>> {
        val httpTransition = HttpTransition(
            verb = HttpVerb.GET,
            url = "${ApiEndpoint.SKYSCANNER.url}/skyscanner-prod-takehome-test/flights.json"
        )

        return _httpTransitionFactory.create(transition = httpTransition)
            .lift(apiErrorOperator())
            .map { SearchAdapter.fromJson(_moshi, it) }
            .subscribeOn(Schedulers.io())
    }

}