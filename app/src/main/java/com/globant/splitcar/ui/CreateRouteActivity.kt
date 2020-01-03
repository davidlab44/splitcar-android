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
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.globant.splitcar.R
import com.globant.splitcar.model.Route
import com.globant.splitcar.utils.CARSEAT
import com.globant.splitcar.utils.DateModel
import com.globant.splitcar.utils.EMAIL
import com.globant.splitcar.utils.PLACES
import com.globant.splitcar.utils.ROUTE_OBJECT
import com.globant.splitcar.utils.ROUTE_ORIGIN
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_route.autoCompleteTextViewDestinationRoute
import kotlinx.android.synthetic.main.activity_create_route.button
import kotlinx.android.synthetic.main.activity_create_route.editTextMeetingPlace
import kotlinx.android.synthetic.main.activity_create_route.imageViewSaveRoute
import kotlinx.android.synthetic.main.activity_create_route.roadReferenceListView
import kotlinx.android.synthetic.main.activity_create_route.roadReferenceSearchInput
import kotlinx.android.synthetic.main.activity_create_route.spinnerCarSeat
import kotlinx.android.synthetic.main.activity_create_route.textViewTimeRoute
import kotlinx.android.synthetic.main.activity_create_route.textViewUser
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
//    private var calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_route)
        val email = intent.getStringExtra(EMAIL)
        bindComponents(email)
        imageViewSaveRoute.setOnClickListener {
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

    //TODO change this component wich is a non focusable dialog for androidx.fragment.app.DialogFragment
    private fun showRoadReferencesDialog() {

        button.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val prev = supportFragmentManager.findFragmentByTag("dialog")
            if (prev != null) {
                fragmentTransaction.remove(prev)
            }
            fragmentTransaction.addToBackStack(null)
            val dialogFragment = MyDialog() //here MyDialog is my custom dialog
            dialogFragment.show(fragmentTransaction, "dialog")
        }
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




