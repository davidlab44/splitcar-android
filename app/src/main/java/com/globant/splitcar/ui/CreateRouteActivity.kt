package com.globant.splitcar.ui

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.globant.splitcar.R
import com.globant.splitcar.model.RoadReferenceRepository
import com.globant.splitcar.model.Route
import com.globant.splitcar.utils.CARSEAT
import com.globant.splitcar.utils.EMAIL
import com.globant.splitcar.utils.ROUTE_OBJECT
import com.globant.splitcar.utils.ROUTE_ORIGIN
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_route.autoCompleteTextViewDestinationRoute
import kotlinx.android.synthetic.main.activity_create_route.button
import kotlinx.android.synthetic.main.activity_create_route.carSeatTextView
import kotlinx.android.synthetic.main.activity_create_route.editTextMeetingPlace
import kotlinx.android.synthetic.main.activity_create_route.imageViewSaveRoute
import kotlinx.android.synthetic.main.activity_create_route.linearLayoutActivityCreateRoute
import kotlinx.android.synthetic.main.activity_create_route.roadReferencesSelectedTextView
import kotlinx.android.synthetic.main.activity_create_route.textViewTimeRoute
import kotlinx.android.synthetic.main.activity_create_route.textViewUser
import java.time.LocalTime
import java.time.format.DateTimeFormatter
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
        showRoadReferencesDialog()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            var roadReferenceText = ""
            RoadReferenceRepository(application).getRoadReferencesSelected().forEach {
                roadReferenceText += "$it "
            }
            roadReferencesSelectedTextView.text = roadReferenceText
        }
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
        val arrayAdapter = ArrayAdapter(this@CreateRouteActivity, android.R.layout.simple_spinner_item, CARSEAT)
        carSeatTextView.adapter = arrayAdapter
        textViewUser.text = email
        var pickHour = LocalTime.now().hour
        var pickMinute = LocalTime.now().minute
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm a")
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        textViewTimeRoute.hint = getText(R.string.pick_time)
        val onTimeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            val (pickTime, pickTimeStringAMPM) = returnPairLocalTimeAndTimeCastAMPM(hourOfDay, minute, timeFormatter)
            if (LocalTime.now() < pickTime) {
                textViewTimeRoute.text = pickTimeStringAMPM
                pickHour = hourOfDay
                pickMinute = minute
            } else {
                Snackbar.make(linearLayoutActivityCreateRoute, getText(R.string.pick_time_after).toString(), Snackbar.LENGTH_LONG).show()
            }
        }
        val timePickerDialog = TimePickerDialog(
                this@CreateRouteActivity,
                onTimeSetListener,
                pickHour,
                pickMinute,
                false
        )
        textViewTimeRoute.setOnClickListener {
            timePickerDialog.show()
        }
    }

    private fun returnPairLocalTimeAndTimeCastAMPM(hourOfDay: Int, minute: Int, timeFormatter: DateTimeFormatter?): Pair<LocalTime, String> {
        val pickTimeString = String.format("%02d", hourOfDay).plus(":").plus(String.format("%02d", minute))
        val pickTime = LocalTime.parse(pickTimeString)
        val pickTimeStringAMPM = pickTime.format(timeFormatter)
        return Pair(pickTime, pickTimeStringAMPM)
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
                "Barbosa",
                ROUTE_ORIGIN,
                textViewTimeRoute.text.toString(),
                carSeatTextView.selectedItem as Int,
                //roadReferenceSearchInput.text.toString(),
                mutableListOf(),
                editTextMeetingPlace.text.toString(),
                mutableListOf()
        )
        firebaseFirestore
                .collection(ROUTE_OBJECT)
                .document(email)
                .set(route)
    }

    override fun onDestroy() {
        super.onDestroy()
        RoadReferenceRepository(application).rollBackRoadReferenceSelected()
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, CreateRouteActivity::class.java)
        }
    }

}
