package io.freshdroid.skyscanner.search.factories

import io.freshdroid.skyscanner.models.Agent
import io.freshdroid.skyscanner.models.Airline
import io.freshdroid.skyscanner.models.Itinerary
import io.freshdroid.skyscanner.models.Leg

object ItineraryFactory {

    fun creator(): ArrayList<Itinerary> {
        return arrayListOf(
            Itinerary(
                "id",
                arrayListOf(
                    Leg(
                        "id",
                        "departureAirport",
                        "arrivalAirport",
                        "departureTime",
                        "arrivalTime",
                        0,
                        Airline(
                            "id",
                            "name"
                        ),
                        180
                    )
                ),
                "£100",
                Agent(
                    "name",
                    9.9
                )
            )
        )
    }

}