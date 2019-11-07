package com.globant.splitcar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globant.splitcar.R
import com.globant.splitcar.model.Route
import kotlinx.android.synthetic.main.route_list_item.view.textViewCarSeat
import kotlinx.android.synthetic.main.route_list_item.view.textViewDateRoute
import kotlinx.android.synthetic.main.route_list_item.view.textViewDestinationRoute
import kotlinx.android.synthetic.main.route_list_item.view.textViewDriverName
import kotlinx.android.synthetic.main.route_list_item.view.textViewTimeRoute


class RoutesAdapter(private val items: ArrayList<Route>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.route_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.driverName.text = items[position].driverName
        holder.destinationRoute.text = items[position].destinationRoute
        holder.dateRoute.text = items[position].dateRoute
        holder.timeRoute.text = "Hora de Salida " + items[position].timeRoute
        holder.carSeat.text = "Cupos " + items[position].carSeat.toString()
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val driverName = view.textViewDriverName
    val destinationRoute = view.textViewDestinationRoute
    val dateRoute = view.textViewDateRoute
    val timeRoute = view.textViewTimeRoute
    val carSeat = view.textViewCarSeat
}
