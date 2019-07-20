package io.freshdroid.skyscanner.models

data class Airline(
    val id: String,
    val name: String
) {
    fun icon(): String = "https://logos.skyscnr.com/images/airlines/favicon/$id.png"
}