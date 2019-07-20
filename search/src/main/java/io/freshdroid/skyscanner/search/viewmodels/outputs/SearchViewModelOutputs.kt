package io.freshdroid.skyscanner.search.viewmodels.outputs

import io.freshdroid.skyscanner.models.Itinerary
import io.reactivex.Observable

interface SearchViewModelOutputs {

    fun itineraries(): Observable<ArrayList<Itinerary>>

    fun displayLoader(): Observable<Boolean>

}