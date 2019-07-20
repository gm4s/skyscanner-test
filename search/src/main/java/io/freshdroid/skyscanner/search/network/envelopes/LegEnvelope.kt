package io.freshdroid.skyscanner.search.network.envelopes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LegEnvelope(
    @Json(name = "id") val id: String? = null,
    @Json(name = "departure_airport") val departureAirport: String? = null,
    @Json(name = "arrival_airport") val arrivalAirport: String? = null,
    @Json(name = "departure_time") val departureTime: String? = null,
    @Json(name = "arrival_time") val arrivalTime: String? = null,
    @Json(name = "stops") val stops: Int? = null,
    @Json(name = "airline_name") val airlineName: String? = null,
    @Json(name = "airline_id") val airlineId: String? = null,
    @Json(name = "duration_mins") val durationMins: Int? = null
)