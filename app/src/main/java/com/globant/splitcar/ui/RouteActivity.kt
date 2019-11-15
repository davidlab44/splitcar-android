package com.globant.splitcar.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.globant.splitcar.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_route.constraintLayoutActivityRoute
import kotlinx.android.synthetic.main.activity_route.spinnerCarSeat
import kotlinx.android.synthetic.main.activity_route.spinnerMeetingPlace
import kotlinx.android.synthetic.main.activity_route.textViewDateRoute
import kotlinx.android.synthetic.main.activity_route.textViewTimeRoute
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class RouteActivity : AppCompatActivity() {
    private val carSeat = arrayOf(1, 2, 3, 4)
    private val meetingPlace = arrayOf("Salida S3", "Salida S2", "Salida S1", "Tostao", "Globant P1", "Globant S2")
    private var calendar = Calendar.getInstance()
    private val currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    private val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Crea tu Ruta"
        val arrayAdapter = ArrayAdapter(this@RouteActivity, android.R.layout.simple_spinner_item, carSeat)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCarSeat.adapter = arrayAdapter
        val arrayAdapterMeetingPlace = ArrayAdapter(this@RouteActivity, android.R.layout.simple_spinner_item, meetingPlace)
        arrayAdapterMeetingPlace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMeetingPlace.adapter = arrayAdapterMeetingPlace
        textViewDateRoute.text = currentDate
        textViewTimeRoute.text = currentTime
        val onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInTextViewDateRoute()
        }
        val onTimeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            updateTimeInTextViewDateRoute()
        }
        textViewDateRoute.setOnClickListener {
            DatePickerDialog(this@RouteActivity, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        textViewTimeRoute.setOnClickListener {
            TimePickerDialog(this@RouteActivity, onTimeSetListener, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE),
                    false).show()
        }
    }

    private fun updateDateInTextViewDateRoute() {
        textViewDateRoute.text = SimpleDateFormat("dd/MM/yyyy", Locale.US).format(calendar.time)
        Snackbar.make(constraintLayoutActivityRoute, SimpleDateFormat("dd/MM/yyyy", Locale.US).format(calendar.time), Snackbar.LENGTH_LONG).show()
    }

    private fun updateTimeInTextViewDateRoute() {
        textViewTimeRoute.text = SimpleDateFormat("HH:mm a", Locale.US).format(calendar.time)
        Snackbar.make(constraintLayoutActivityRoute, SimpleDateFormat("HH:mm a", Locale.US).format(calendar.time), Snackbar.LENGTH_LONG).show()
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

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, RouteActivity::class.java)
        }
    }
}
