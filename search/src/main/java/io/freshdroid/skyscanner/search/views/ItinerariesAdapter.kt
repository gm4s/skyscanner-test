package io.freshdroid.skyscanner.search.views

import android.view.View
import io.freshdroid.skyscanner.core.ui.BaseArrayAdapter
import io.freshdroid.skyscanner.core.ui.BaseViewHolder
import io.freshdroid.skyscanner.models.Itinerary
import io.freshdroid.skyscanner.search.R

class ItinerariesAdapter(
    private val _listener: Listener?
) : BaseArrayAdapter<Itinerary>() {

    interface Listener : ItineraryViewHolder.Listener

    override fun layout(item: Itinerary): Int {
        return R.layout.list_item_itinerary
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return ItineraryViewHolder(view, _listener)
    }

    fun takeItems(items: ArrayList<Itinerary>) {
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

}