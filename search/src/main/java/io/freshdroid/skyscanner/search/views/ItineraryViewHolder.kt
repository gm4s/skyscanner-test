package io.freshdroid.skyscanner.search.views

import android.view.View
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView
import io.freshdroid.skyscanner.core.extensions.bind
import io.freshdroid.skyscanner.core.extensions.loadPicture
import io.freshdroid.skyscanner.core.ui.BaseViewHolder
import io.freshdroid.skyscanner.models.Itinerary
import io.freshdroid.skyscanner.search.R

class ItineraryViewHolder(
    view: View,
    private val _listener: Listener?
) : BaseViewHolder(view) {

    interface Listener

    private val _legsTableLayout: TableLayout by bind(R.id.legsTableLayout)
    private val _priceTextView: TextView by bind(R.id.priceTextView)
    private val _agentNameTextView: TextView by bind(R.id.agentNameTextView)

    private lateinit var _itinerary: Itinerary

    override fun bindData(data: Any) {
        _itinerary = requireNotNull(data as Itinerary)
    }

    override fun onBind() {
        _itinerary.legs.forEach { leg ->
            val tableRow = View.inflate(context(), R.layout.row_item_leg, null)

            val iconAirlineImageView: ImageView = tableRow.findViewById(R.id.iconAirlineImageView)
            val stopsTextView: TextView = tableRow.findViewById(R.id.stopsTextView)

            iconAirlineImageView.loadPicture(leg.airline.icon())
            stopsTextView.text = if (leg.stops == 0) "Direct" else "${leg.stops} stops" // TODO use string localized /en /fr etc.

            _legsTableLayout.addView(tableRow)


        }

        _priceTextView.text = _itinerary.price
        _agentNameTextView.text = _itinerary.agent.name
    }

}