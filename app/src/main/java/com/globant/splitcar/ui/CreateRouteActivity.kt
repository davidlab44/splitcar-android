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
import com.globant.splitcar.model.currentDate
import com.globant.splitcar.utils.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_route.*
import java.text.SimpleDateFormat
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
    private var calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_route)
        bindComponents()
        imageViewSaveRoute.setOnClickListener {
            saveFireStore()
            finish()
        }

        roadReferenceSearchInput.hint = "Search.."
        var galaxies = arrayOf(
            "Sombrero",
            "Cartwheel",
            "Pinwheel",
            "StarBust",
            "Whirlpool",
            "Ring Nebular",
            "Own Nebular",
            "Centaurus A",
            "Virgo Stellar Stream",
            "Canis Majos Overdensity",
            "Mayall's Object",
            "Leo",
            "Milky Way",
            "IC 1011",
            "Messier 81",
            "Andromeda",
            "Messier 87"
        )
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, galaxies)
        roadReferenceListView.adapter = adapter

        roadReferenceSearchInput.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, s: Int, b: Int, c: Int) {
                adapter.filter.filter(charSequence)
            }

            override fun afterTextChanged(editable: Editable) {}
            override fun beforeTextChanged(
                cs: CharSequence,
                i: Int,
                j: Int,
                k: Int
            ) {
            }
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
    }

    private fun bindComponents() {
        // TODO esto es una variable de clase
        val email = intent.getStringExtra(EMAIL)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getText(R.string.create_route)
        textViewUser.text = email
        val arrayAdapter =
            ArrayAdapter(this@CreateRouteActivity, android.R.layout.simple_spinner_item, CARSEAT)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCarSeat.adapter = arrayAdapter
        textViewDateRoute.text = CURRENTDATE
        textViewTimeRoute.text = CURRENTTIME
        val onTimeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            updateTimeInTextViewDateRoute()
        }
        textViewTimeRoute.setOnClickListener {
            TimePickerDialog(
                this@CreateRouteActivity,
                    onTimeSetListener,
                    calendar.get(Calendar.HOUR),
                    calendar.get(Calendar.MINUTE),
                    false
            ).show()
        }
        // TODO Hacerle update al timepicker con lo que selecciona el usuario
        // TODO validar que la fecha seleccionada solo sea la de hoy y que la hora no sea anterior a la hora actual
        // TODO quitar el calendar que no se necesita
        // TODO un solo timepicker dialog tenerlo por fuera del onClickListener OJO sacar del listener la creacion de la instancia del timepicker
    }

    private fun updateTimeInTextViewDateRoute() {
        textViewTimeRoute.text = SimpleDateFormat("HH:mm a", Locale.US).format(calendar.time)
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
            currentDate,
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
