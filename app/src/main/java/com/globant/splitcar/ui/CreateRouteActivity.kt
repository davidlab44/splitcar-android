package com.globant.splitcar.ui

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.globant.splitcar.R
import com.globant.splitcar.model.Route
import com.globant.splitcar.utils.CARSEAT
import com.globant.splitcar.utils.EMAIL
import com.globant.splitcar.utils.PLACES
import com.globant.splitcar.utils.ROUTE_OBJECT
import com.globant.splitcar.utils.ROUTE_ORIGIN
import com.globant.splitcar.utils.TIMEPICKERHOUR
import com.globant.splitcar.utils.TIMEPICKERMINUTE
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_route.autoCompleteTextViewDestinationRoute
import kotlinx.android.synthetic.main.activity_create_route.button
import kotlinx.android.synthetic.main.activity_create_route.editTextMeetingPlace
import kotlinx.android.synthetic.main.activity_create_route.imageViewSaveRoute
import kotlinx.android.synthetic.main.activity_create_route.spinnerCarSeat
import kotlinx.android.synthetic.main.activity_create_route.textViewTimeRoute
import kotlinx.android.synthetic.main.activity_create_route.textViewUser
import kotlinx.android.synthetic.main.activity_route.linearLayoutActivityRoute
import kotlinx.android.synthetic.main.activity_route.roadReferenceSearchInput
import kotlinx.android.synthetic.main.road_references_dialog.roadReferenceListView
import java.time.LocalTime
import java.util.UUID

/**
 * CreateRouteActivity
 *
 * This class create a route by a driver user
 *
 * @author david.mazo
 */

class CreateRouteActivity : AppCompatActivity() {
    private val firebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_route)
        val email = intent.getStringExtra(EMAIL)
        bindComponents(email)
        imageViewSaveRoute.setOnClickListener {
            lauchDialog()
        }
        autoCompleteTextViewDestinationRoute.setOnClickListener {
            saveFireStore()
            finish()
        }

        roadReferenceSearchInput.hint = "Search.."
        val places = PLACES
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, places)
        roadReferenceListView.adapter = adapter
        roadReferenceSearchInput.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, s: Int, b: Int, c: Int) {
                adapter.filter.filter(charSequence)
            }

            override fun afterTextChanged(editable: Editable) {}
            override fun beforeTextChanged(cs: CharSequence, i: Int, j: Int, k: Int) {}
        })

        roadReferenceListView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                Toast.makeText(
                        this@CreateRouteActivity,
                        adapter.getItem(i)!!.toString(),
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
        showRoadReferencesDialog()
    }

    private fun showRoadReferencesDialog() {
        button.setOnClickListener {
            lauchDialog()
        }
    }

    private fun lauchDialog() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val prev = supportFragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            fragmentTransaction.remove(prev)
        }
        fragmentTransaction.addToBackStack(null)
        val dialogFragment = RoadReferencesDialog(application)
        dialogFragment.show(fragmentTransaction, "dialog")
    }

    private fun bindComponents(email: String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getText(R.string.create_route)
        textViewUser.text = email
        val arrayAdapter =
                ArrayAdapter(this@CreateRouteActivity, android.R.layout.simple_spinner_item, CARSEAT)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCarSeat.adapter = arrayAdapter
        textViewTimeRoute.hint = "Elige una hora"
        val onTimeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            val pickTimeString = String.format("%02d", hourOfDay).plus(":").plus(String.format("%02d", minute))
            val pickTimeLocalTime = LocalTime.parse(pickTimeString)
            val localTime = LocalTime.now()
            if (localTime < pickTimeLocalTime) {
                textViewTimeRoute.text = pickTimeString
                TIMEPICKERHOUR = hourOfDay
                TIMEPICKERMINUTE = minute
            } else {
                Snackbar.make(linearLayoutActivityRoute, "Selecciona una hora posterior", Snackbar.LENGTH_LONG).show()
            }
        }
        val timePickerDialog = TimePickerDialog(
                this@CreateRouteActivity,
                onTimeSetListener,
                TIMEPICKERHOUR,
                TIMEPICKERMINUTE,
                false
        )
        textViewTimeRoute.setOnClickListener {
            timePickerDialog.show()
        }
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }

    private fun saveFireStore() {
        val id = UUID.randomUUID().toString()
        val email = textViewUser.text.toString()
        val route = Route(
                id,
                email,
                autoCompleteTextViewDestinationRoute.text.toString(),
                ROUTE_ORIGIN,
                textViewTimeRoute.text.toString(),
                spinnerCarSeat.selectedItem as Int,
                roadReferenceSearchInput.text.toString(),
                editTextMeetingPlace.text.toString(),
                mutableListOf()
        )
        firebaseFirestore
                .collection(ROUTE_OBJECT)
                .document(email)
                .set(route)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, CreateRouteActivity::class.java)
        }
    }

}
