package io.freshdroid.skyscanner.search.network.adapters

import com.squareup.moshi.Moshi
import io.freshdroid.skyscanner.models.Itinerary
import io.freshdroid.skyscanner.models.Leg
import io.freshdroid.skyscanner.search.network.envelopes.SearchEnvelope

object SearchAdapter {

    fun fromJson(moshi: Moshi, json: String): ArrayList<Itinerary> {
        val searchEnvelope = moshi.adapter(SearchEnvelope::class.java).fromJson(json)
        val itineraries = ArrayList<Itinerary>()
        val legs = ArrayList<Leg>()

        searchEnvelope?.let {
            it.legs?.forEach { legEnvelope ->
                legs.add(LegAdapter.fromEnvelope(legEnvelope))
            }
            it.itineraries?.forEach { itineraryEnvelope ->
                itineraries.add(ItineraryAdapter.fromEnvelope(itineraryEnvelope, legs))
            }
        }

        return itineraries
    }

}