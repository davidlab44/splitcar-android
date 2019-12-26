package com.globant.splitcar.ui

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.globant.splitcar.R
import com.globant.splitcar.model.Route
import com.globant.splitcar.model.routes
import com.globant.splitcar.utils.CARSEAT
import com.globant.splitcar.utils.CURRENTDATE
import com.globant.splitcar.utils.CURRENTTIME
import com.globant.splitcar.utils.EMAIL
import com.globant.splitcar.utils.ROUTE_OBJECT
import com.globant.splitcar.utils.ROUTE_ORIGIN
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_route.textViewUser
import kotlinx.android.synthetic.main.activity_route1.autoCompleteTextViewDestinationRoute
import kotlinx.android.synthetic.main.activity_route1.editTextDestinationReference
import kotlinx.android.synthetic.main.activity_route1.editTextMeetingPlace
import kotlinx.android.synthetic.main.activity_route1.imageViewSaveRoute
import kotlinx.android.synthetic.main.activity_route1.spinnerCarSeat
import kotlinx.android.synthetic.main.activity_route1.textViewDateRoute
import kotlinx.android.synthetic.main.activity_route1.textViewTimeRoute
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * RouteActivity
 *
 * It was created with the aim of an authenticated driver creating a route in firebase
 * @author juan.rendon
 */

class RouteActivity : AppCompatActivity() {
    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private var calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route1)
        bindComponents()
        imageViewSaveRoute.setOnClickListener {
            saveFireStore()
            finish()
        }
    }

    private fun bindComponents() {
        val email = intent.getStringExtra(EMAIL)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getText(R.string.create_route)
        textViewUser.text = email
        val arrayAdapter = ArrayAdapter(this@RouteActivity, android.R.layout.simple_spinner_item, CARSEAT)
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
                    this@RouteActivity,
                    onTimeSetListener,
                    calendar.get(Calendar.HOUR),
                    calendar.get(Calendar.MINUTE),
                    false
            ).show()
        }
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
        val id: Long = routes.size + 1.toLong()
        val email = textViewUser.text.toString()
        val route = Route(
                id,
                email,
                autoCompleteTextViewDestinationRoute.text.toString(),
                ROUTE_ORIGIN,
                com.globant.splitcar.model.currentDate,
                textViewTimeRoute.text.toString(),
                spinnerCarSeat.selectedItem as Long,
                editTextDestinationReference.text.toString(),
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
            return Intent(context, RouteActivity::class.java)
        }
    }
}
