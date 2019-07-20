package io.freshdroid.skyscanner.search.network

import io.freshdroid.skyscanner.models.Itinerary
import io.reactivex.Observable

interface ApiSearchType {

    fun fetchItineraries(): Observable<ArrayList<Itinerary>>

}