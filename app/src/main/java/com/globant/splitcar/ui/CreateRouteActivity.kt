package com.globant.splitcar.ui

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.globant.splitcar.R
import com.globant.splitcar.model.Route
import com.globant.splitcar.utils.CARSEAT
import com.globant.splitcar.utils.CURRENTTIME
import com.globant.splitcar.utils.EMAIL
import com.globant.splitcar.utils.ROUTE_OBJECT
import com.globant.splitcar.utils.ROUTE_ORIGIN
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_route.autoCompleteTextViewDestinationRoute
import kotlinx.android.synthetic.main.activity_create_route.button
import kotlinx.android.synthetic.main.activity_create_route.editTextMeetingPlace
import kotlinx.android.synthetic.main.activity_create_route.imageViewSaveRoute
import kotlinx.android.synthetic.main.activity_create_route.roadReferenceListView
import kotlinx.android.synthetic.main.activity_create_route.roadReferenceSearchInput
import kotlinx.android.synthetic.main.activity_create_route.root_layout
import kotlinx.android.synthetic.main.activity_create_route.spinnerCarSeat
import kotlinx.android.synthetic.main.activity_create_route.textViewTimeRoute
import kotlinx.android.synthetic.main.activity_create_route.textViewUser
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
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
    private var calendar = Calendar.getInstance()

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
            // Initialize a new layout inflater instance
            val inflater: LayoutInflater =
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            // Inflate a custom view using layout inflater
            val view = inflater.inflate(R.layout.another_view, null)

            // Initialize a new instance of popup window
            val popupWindow = PopupWindow(
                    view, // Custom view to show in popup window
                    LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
                    LinearLayout.LayoutParams.WRAP_CONTENT // Window height
            )

            // Set an elevation for the popup window
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.elevation = 10.0F
            }

            // If API level 23 or higher then execute the code
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Create a new slide animation for popup window enter transition
                val slideIn = Slide()
                slideIn.slideEdge = Gravity.TOP
                popupWindow.enterTransition = slideIn

                // Slide animation for popup window exit transition
                val slideOut = Slide()
                slideOut.slideEdge = Gravity.RIGHT
                popupWindow.exitTransition = slideOut

            }

            // Get the widgets reference from custom view
            val tv = view.findViewById<TextView>(R.id.text_view)
            val buttonPopup = view.findViewById<Button>(R.id.button_popup)

            // Set click listener for popup window's text view
            tv.setOnClickListener {
                // Change the text color of popup window's text view
                tv.setTextColor(Color.RED)
            }

            // Set a click listener for popup's button widget
            buttonPopup.setOnClickListener {
                // Dismiss the popup window
                popupWindow.dismiss()
            }

            // Set a dismiss listener for popup window
            popupWindow.setOnDismissListener {
                Toast.makeText(applicationContext, "Popup closed", Toast.LENGTH_SHORT).show()
            }

            // Finally, show the popup window on app
            TransitionManager.beginDelayedTransition(root_layout)
            popupWindow.showAtLocation(
                    root_layout, // Location to display popup window
                    Gravity.CENTER, // Exact position of layout to display popup
                    0, // X offset
                    0 // Y offset
            )
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
