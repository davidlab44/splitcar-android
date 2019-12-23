package com.globant.splitcar.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globant.splitcar.R
import com.globant.splitcar.model.Route
import com.globant.splitcar.ui.RouteEvents
import kotlinx.android.synthetic.main.route_list_item.view.*

/**
 * RouteListAdapter
 *
 * connect the source of the route list founded in database firestore with the instantiated class firebaseFirestore.collection routes ("Route") that
 * will be displayed in the recyclerViewRoutes route list found in the MainActivity activity and in the XML activity_main. xml
 *
 * @author juan.rendon
 */

class RouteListAdapter(private val routeEvents: RouteEvents) : RecyclerView.Adapter<RouteListAdapter.ViewHolder>() {

    private var listRoute: List<Route> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.route_list_item, parent, false))
    }

    override fun getItemCount() = listRoute.size

    fun addAll(listRoute: List<Route>) {
        this.listRoute = listRoute
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listRoute[position], routeEvents)
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(route: Route, listener: RouteEvents) {
            itemView.textViewDriverName.text = route.driverName
            itemView.textViewDestinationRoute.text = route.destinationRoute
            itemView.textViewDateRoute.text = route.dateRoute
            itemView.textViewTimeRoute.text = Resources.getSystem().getString(R.string.departure_time) + route.timeRoute
            itemView.spinnerCarSeat.text = Resources.getSystem().getString(R.string.available_positions) + route.carSeat.toString()
            view.setOnClickListener { listener.onItemClicked(route) }
        }
    }
}
