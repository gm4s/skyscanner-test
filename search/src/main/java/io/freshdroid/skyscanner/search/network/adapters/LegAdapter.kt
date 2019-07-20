package io.freshdroid.skyscanner.search.network.adapters

import io.freshdroid.skyscanner.models.Airline
import io.freshdroid.skyscanner.models.Leg
import io.freshdroid.skyscanner.search.network.envelopes.LegEnvelope

object LegAdapter {

    @Throws(RuntimeException::class)
    fun fromEnvelope(legEnvelope: LegEnvelope): Leg {
        return Leg(
            legEnvelope.id ?: throw RuntimeException("leg id missing"),
            legEnvelope.departureAirport ?: throw RuntimeException("leg departure_airport missing"),
            legEnvelope.arrivalAirport ?: throw RuntimeException("leg arrival_airport missing"),
            legEnvelope.departureTime ?: throw RuntimeException("leg departure_time missing"),
            legEnvelope.arrivalTime ?: throw RuntimeException("leg arrival_time missing"),
            legEnvelope.stops ?: throw RuntimeException("leg stops missing"),
            airlineFromEnvelope(legEnvelope),
            legEnvelope.durationMins ?: throw RuntimeException("leg duration_mins missing")
        )
    }

    private fun airlineFromEnvelope(legEnvelope: LegEnvelope): Airline {
        return Airline(
            legEnvelope.airlineId ?: throw RuntimeException("leg airline_id missing"),
            legEnvelope.airlineName ?: throw RuntimeException("leg airline_name missing")
        )
    }

}