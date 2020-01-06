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
import com.globant.splitcar.model.RouteRepository
import com.globant.splitcar.utils.PLACES
import kotlinx.android.synthetic.main.road_references_dialog.closeDialogButton
import kotlinx.android.synthetic.main.road_references_dialog.roadReferenceListView
import kotlinx.android.synthetic.main.road_references_dialog.roadReferenceSearchInput

class RoadReferencesDialog(private val application: Application) : DialogFragment() {
    /*
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MyDialog().
    }
    */

    var placesList: MutableList<String> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.road_references_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        roadReferenceSearchInput.hint = "Search.."
        val adapter = createLisViewAdapter()
        listenTextChange(adapter)
        listenRoadReferenceListView(adapter)
        closeDialogButton.setOnClickListener { dismiss() }
    }

    private fun createLisViewAdapter(): ArrayAdapter<String> {
        val adapter = ArrayAdapter<String>(application.applicationContext, android.R.layout.simple_list_item_1, PLACES)
        roadReferenceListView.adapter = adapter
        return adapter
    }

    private fun listenTextChange(adapter: ArrayAdapter<String>) {
        roadReferenceSearchInput.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, s: Int, b: Int, c: Int) {
                adapter.filter.filter(charSequence)
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