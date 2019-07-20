package io.freshdroid.skyscanner.search.mocks

import io.freshdroid.skyscanner.models.Itinerary
import io.freshdroid.skyscanner.search.network.ApiSearchType
import io.reactivex.Observable

open class MockApiSearch : ApiSearchType {

    override fun fetchItineraries(): Observable<ArrayList<Itinerary>> {
        return Observable.empty()
    }

}