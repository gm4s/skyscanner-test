package io.freshdroid.skyscanner.search.viewmodels.intputs

import io.freshdroid.skyscanner.search.views.ItinerariesAdapter

interface SearchViewModelInputs : ItinerariesAdapter.Listener {

    fun fetchRoundTripTicketBUDToLDN()

}