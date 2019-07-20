package io.freshdroid.skyscanner.search.network.envelopes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchEnvelope(
    @Json(name = "itineraries") val itineraries: List<ItineraryEnvelope>? = listOf(),
    @Json(name = "legs") val legs: List<LegEnvelope>? = listOf()
)