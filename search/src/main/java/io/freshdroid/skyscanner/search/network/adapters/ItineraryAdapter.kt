package io.freshdroid.skyscanner.search.network.adapters

import io.freshdroid.skyscanner.models.Agent
import io.freshdroid.skyscanner.models.Itinerary
import io.freshdroid.skyscanner.models.Leg
import io.freshdroid.skyscanner.search.network.envelopes.ItineraryEnvelope

object ItineraryAdapter {

    fun fromEnvelope(itineraryEnvelope: ItineraryEnvelope, legs: ArrayList<Leg>): Itinerary {
        return Itinerary(
            itineraryEnvelope.id ?: throw RuntimeException("itinerary id missing"),
            itineraryEnvelope.legs?.let { parseLegs(it, legs) } ?: throw RuntimeException("itinerary legs missing"),
            itineraryEnvelope.price ?: throw RuntimeException("itinerary price missing"),
            parseAgent(itineraryEnvelope)
        )
    }

    private fun parseLegs(legIds: List<String>, legs: ArrayList<Leg>): ArrayList<Leg> {
        val itineraryLegs = ArrayList<Leg>()
        legIds.forEach { legId ->
            legs.find { leg -> leg.id == legId }?.let {
                itineraryLegs.add(it)
            }
        }
        return itineraryLegs
    }

    private fun parseAgent(itineraryEnvelope: ItineraryEnvelope): Agent {
        return Agent(
            itineraryEnvelope.agent ?: throw RuntimeException("itinerary agent name missing"),
            itineraryEnvelope.agentRating ?: throw RuntimeException("itinerary agent rating missing")
        )
    }

}