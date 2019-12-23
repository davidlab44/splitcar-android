package com.globant.splitcar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globant.splitcar.R
import kotlinx.android.synthetic.main.passenger_list_item.view.*

/**
 * PassengerAdapter
 * connect the source of the passenger list that is in the array of strings within the firebaseFirestore.collection ("Route"). document (email)
 * instance to be displayed in the recyclerview passenger_list that is in the JoinRouteActivity activity and in the XML activity_join_route.xml
 *
 * @author juan.rendon
 */

class PassengerAdapter(
    private val mutableListPassengerName: MutableList<String>,
    val context: Context
) : RecyclerView.Adapter<PassengerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.passenger_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mutableListPassengerName.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewPassengerName.text = mutableListPassengerName[position]
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textViewPassengerName = view.passenger_name
    }
}
