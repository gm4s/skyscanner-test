package io.freshdroid.skyscanner.search.network.envelopes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItineraryEnvelope(
    @Json(name = "id") val id: String? = null,
    @Json(name = "legs") val legs: List<String>? = listOf(),
    @Json(name = "price") val price: String? = null,
    @Json(name = "agent") val agent: String? = null,
    @Json(name = "agent_rating") val agentRating: Double? = null
)