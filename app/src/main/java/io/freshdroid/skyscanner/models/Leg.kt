package io.freshdroid.skyscanner.models

data class Leg(
    val id: String,
    val departureAirport: String,
    val arrivalAirport: String,
    val departureTime: String,
    val arrivalTime: String,
    val stops: Int,
    val airline: Airline,
    val durationInMinutes: Int
)