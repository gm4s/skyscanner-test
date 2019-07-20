package io.freshdroid.skyscanner.models

data class Itinerary(
    val id: String,
    val legs: ArrayList<Leg>,
    val price: String,
    val agent: Agent
)