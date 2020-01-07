package com.globant.splitcar.ui

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.globant.splitcar.R
import com.globant.splitcar.model.RoadReferenceRepository
import com.globant.splitcar.model.Route
import com.globant.splitcar.utils.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_route.*
import kotlinx.android.synthetic.main.activity_create_route.autoCompleteTextViewDestinationRoute
import kotlinx.android.synthetic.main.activity_create_route.editTextMeetingPlace
import kotlinx.android.synthetic.main.activity_create_route.imageViewSaveRoute
import kotlinx.android.synthetic.main.activity_create_route.spinnerCarSeat
import kotlinx.android.synthetic.main.activity_create_route.textViewTimeRoute
import kotlinx.android.synthetic.main.activity_create_route.textViewUser
import kotlinx.android.synthetic.main.activity_route.roadReferenceSearchInput
import java.util.*

/**
 * CreateRouteActivity
 *
 * This class create a route by a driver user
 *
 * @author david.mazo
 */

class CreateRouteActivity : AppCompatActivity() {
    private val firebaseFirestore = FirebaseFirestore.getInstance()
    //private var calendar = Calendar.getInstance()

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
        showRoadReferencesDialog()
    }

    //TODO change this component wich is a non focusable dialog for androidx.fragment.app.DialogFragment
    private fun showRoadReferencesDialog() {
        button.setOnClickListener {
            lauchDialog()
        }
    }

    private fun lauchDialog() {
        val records = RoadReferenceRepository(application).getRoadReferenceList()
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
        val timePicker = TimePicker(this@CreateRouteActivity)
        textViewTimeRoute.hint = "Elige una hora"
        val onTimeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, _, _ ->
            val dateModel = DateModel()
            dateModel.hour = timePicker.hour
            dateModel.minute = timePicker.minute
            textViewTimeRoute.text = "${dateModel.hour}:${dateModel.minute}"
        }
        textViewTimeRoute.setOnClickListener {
            TimePickerDialog(
                    this@CreateRouteActivity,
                    onTimeSetListener,
                    timePicker.hour,
                    timePicker.minute,
                    false
            ).show()
        }
    }

    // TODO Hacerle update al timepicker con lo que selecciona el usuario
    // TODO validar que la fecha seleccionada solo sea la de hoy y que la hora no sea anterior a la hora actual
    // TODO quitar el calendar que no se necesita
    // TODO un solo timepicker dialog tenerlo por fuera del onClickListener OJO sacar del listener la creacion de la instancia del timepicker
    // TODO Delete Screen Firebase

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




