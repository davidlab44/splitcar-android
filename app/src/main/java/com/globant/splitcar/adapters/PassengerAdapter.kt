package com.globant.splitcar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globant.splitcar.R
import kotlinx.android.synthetic.main.passenger_list_item.view.passenger_name

class PassengerAdapter(private val passenegerName: MutableList<String?>, val context: Context) : RecyclerView.Adapter<PassengerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.passenger_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return passenegerName.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.passengerName?.text = passenegerName[position]

    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val passengerName = view.passenger_name
    }
}