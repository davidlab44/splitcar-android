package com.globant.splitcar.ui

import android.app.Application
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.globant.splitcar.R
import com.globant.splitcar.model.RoadReferenceRepository
import com.globant.splitcar.model.local.RoadReference
import kotlinx.android.synthetic.main.road_references_dialog.closeDialogButton
import kotlinx.android.synthetic.main.road_references_dialog.roadReferenceListView
import kotlinx.android.synthetic.main.road_references_dialog.roadReferenceSearchInput

class RoadReferencesDialog(private val application: Application) : DialogFragment() {

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
        listenTextChanges()
        selectRoadReference(adapter)
        closeDialogButton.setOnClickListener { dismiss() }
    }

    private fun createLisViewAdapter(roadReferenceHash: String): ArrayAdapter<RoadReference> {
        return ArrayAdapter(application.applicationContext, android.R.layout.simple_list_item_1, RoadReferenceRepository(application).getFilteredRoadReferenceList(roadReferenceHash))
    }

    private fun listenTextChanges() {
        roadReferenceSearchInput.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, s: Int, b: Int, c: Int) {
                if (charSequence.isNotEmpty())
                    roadReferenceListView.adapter = createLisViewAdapter(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable) {}
            override fun beforeTextChanged(cs: CharSequence, i: Int, j: Int, k: Int) {}
        })
    }

    private fun selectRoadReference(adapter: ArrayAdapter<RoadReference>) {
        roadReferenceListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
            RoadReferenceRepository(application).updateRoadReferenceSelectedField(adapter.getItem(i)!!.toString())
        }
    }
}
