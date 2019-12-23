package com.globant.splitcar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globant.splitcar.R
import com.globant.splitcar.model.Route
import com.globant.splitcar.ui.RouteEvents
import kotlinx.android.synthetic.main.route_list_item.view.spinnerCarSeat
import kotlinx.android.synthetic.main.route_list_item.view.textViewDateRoute
import kotlinx.android.synthetic.main.route_list_item.view.textViewDestinationRoute
import kotlinx.android.synthetic.main.route_list_item.view.textViewDriverName
import kotlinx.android.synthetic.main.route_list_item.view.textViewTimeRoute


class RouteListAdapter(private val routeEvents: RouteEvents) : RecyclerView.Adapter<RouteListAdapter.ViewHolder>() {

    private var listRoute: List<Route> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.route_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listRoute.size
    }

    fun addAll(listRoute: List<Route>) {
        this.listRoute = listRoute
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listRoute[position], routeEvents)
    }

//    private fun saveFireStore() {
//        val id: Long = routes.size + 1.toLong()
//        val email = editTextUser.text.toString()
//        val route = Route(
//                id,
//                email,
//                autoCompleteTextViewDestinationRoute.text.toString(),
//                "IUSH",
//                com.globant.splitcar.model.currentDate,
//                textViewTimeRoute.text.toString(),
//                spinnerCarSeat.selectedItem as Long,
//                editTextDestinationReference.text.toString(),
//                editTextMeetingPlace.text.toString()
//        )
//        firebaseFirestore
//                .collection("Route")
//                .document(email)
//                .set(route)
//    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(route: Route, listener: RouteEvents) {
            itemView.textViewDriverName.text = route.driverName
            itemView.textViewDestinationRoute.text = route.destinationRoute
            itemView.textViewDateRoute.text = route.dateRoute
            itemView.textViewTimeRoute.text = "Hora de Salida " + route.timeRoute
            itemView.spinnerCarSeat.text = "Cupos " + route.carSeat.toString()
            view.setOnClickListener { listener.onItemClicked(route) }
        }
    }
}