package com.globant.splitcar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.globant.splitcar.R
import kotlinx.android.synthetic.main.another_view.closeDialogButton

class MyDialog : DialogFragment() {
    /*
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MyDialog().
    }
    */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //here fragment_my_dialog is the UI of Custom Dialog
        return inflater.inflate(R.layout.another_view, container, false)
    }

    override fun onResume() {
        super.onResume()
        closeDialogButton.setOnClickListener {
            dismiss()
        }
    }
}