package com.globant.splitcar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.globant.splitcar.R
import kotlinx.android.synthetic.main.road_reference_item.view.roadReferenceItem

/**
 * RouteReferenceAdapter
 * connect the source of the road references list that is in the array of strings within the firebaseFirestore.collection ("Route"). document (email)
 * instance to be displayed in the recyclerview road_reference_list within JoinRouteActivity activity and in the XML activity_join_route.xml
 *
 * @author david.mazo
 */

class RoadReferenceAdapter : RecyclerView.Adapter<RoadReferenceAdapter.ViewHolder>() {
    private var mutableListRoadReference = mutableListOf<String>()

    fun addAll(roadReferenceList: MutableList<String>) {
        this.mutableListRoadReference = roadReferenceList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.road_reference_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mutableListRoadReference.size
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textViewPassengerName = view.roadReferenceItem
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
