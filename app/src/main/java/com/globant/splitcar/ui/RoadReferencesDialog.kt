package com.globant.splitcar.ui

import android.app.Application
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.globant.splitcar.R
import com.globant.splitcar.model.RoadReferenceRepository
import com.globant.splitcar.model.RouteRepository
import com.globant.splitcar.model.local.RoadReference
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

    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        dialog.setOnDismissListener {

        }
        dialog.setOnCancelListener {

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        roadReferenceSearchInput.hint = application.applicationContext.getString(R.string.search)
        val adapter = createLisViewAdapter("")
        roadReferenceListView.adapter = adapter
        listenTextChange()
        addRoadReferenceToArray(adapter)
        closeDialogButton.setOnClickListener { dismiss() }
    }

    private fun createLisViewAdapter(roadReferenceHash: String): ArrayAdapter<RoadReference> {
        return ArrayAdapter(application.applicationContext, android.R.layout.simple_list_item_1, RoadReferenceRepository(application).getFilteredRoadReferenceList(roadReferenceHash))
    }

    private fun listenTextChange() {
        roadReferenceSearchInput.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, s: Int, b: Int, c: Int) {
                if (charSequence.isNotEmpty())
                    roadReferenceListView.adapter = createLisViewAdapter(charSequence.toString())
            }
            override fun afterTextChanged(editable: Editable) {}
            override fun beforeTextChanged(cs: CharSequence, i: Int, j: Int, k: Int) {}
        })
    }

    private fun addRoadReferenceToArray(adapter: ArrayAdapter<RoadReference>) {
        roadReferenceListView.onItemClickListener = AdapterView.OnItemClickListener { a, b, i, x ->
            placesList.add(adapter.getItem(i)!!.toString())
            RoadReferenceRepository(application).updateRoadReferenceSelectedField(adapter.getItem(i)!!.toString())
            val cant = RoadReferenceRepository(application).getRoadReferencesSelected().size
            Log.e("TAG #33", "$placesList")
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        RouteRepository().saveRoadReferences(placesList)
    }
}
