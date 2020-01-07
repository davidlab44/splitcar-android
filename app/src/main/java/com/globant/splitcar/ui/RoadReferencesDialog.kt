package com.globant.splitcar.ui

import android.app.Application
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.globant.splitcar.R
import com.globant.splitcar.model.RoadReferenceRepository
import com.globant.splitcar.model.RouteRepository
import kotlinx.android.synthetic.main.road_references_dialog.closeDialogButton
import kotlinx.android.synthetic.main.road_references_dialog.roadReferenceListView
import kotlinx.android.synthetic.main.road_references_dialog.roadReferenceSearchInput

class RoadReferencesDialog(private val application: Application) : DialogFragment() {

    var placesList: MutableList<String> = mutableListOf()

    //override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {}

    //override fun onCreate(savedInstanceState: Bundle?) {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.road_references_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        roadReferenceSearchInput.hint = "Search.."
        val adapter = createLisViewAdapter()
        listenTextChange()
        listenRoadReferenceListView(adapter)
        closeDialogButton.setOnClickListener { dismiss() }
    }

    private fun createLisViewAdapter(): ArrayAdapter<String> {
        //TODO get list of 10 places
        val list = RoadReferenceRepository(application).getRoadReferenceList()
        list.size
        val array = arrayOfNulls<String>(list.size)
        var iterator = 0
        list.forEach {
            array[iterator] = it.name
            iterator++
        }
        val ss = array.size
        val adapter = ArrayAdapter<String>(application.applicationContext, android.R.layout.simple_list_item_1, array)
        roadReferenceListView.adapter = adapter
        return adapter
    }

    private fun listenTextChange() {
        roadReferenceSearchInput.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, s: Int, b: Int, c: Int) {
                //if(charSequence.isNotEmpty())
                //adapter.filter.filter(charSequence)
                //roadReferenceListView.adapter = createLisViewAdapter(charSequence.toString())
                //val s = "ss"
            }
            override fun afterTextChanged(editable: Editable) {}
            override fun beforeTextChanged(cs: CharSequence, i: Int, j: Int, k: Int) {}
        })
    }

    private fun listenRoadReferenceListView(adapter: ArrayAdapter<String>) {
        roadReferenceListView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                Toast.makeText(application.applicationContext, adapter.getItem(i)!!.toString(), Toast.LENGTH_SHORT).show()
                placesList.add(adapter.getItem(i)!!.toString())
            }
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        RouteRepository().saveRoadReferences(placesList)
    }
}