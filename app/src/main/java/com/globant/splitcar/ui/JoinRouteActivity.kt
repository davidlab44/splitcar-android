package com.globant.splitcar.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.globant.splitcar.R
import com.globant.splitcar.adapters.PassengerAdapter
import com.globant.splitcar.model.Route
import com.globant.splitcar.utils.ID_USER
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_join_route.buttonSaveRoute
import kotlinx.android.synthetic.main.activity_join_route.passenger_list
import kotlinx.android.synthetic.main.activity_join_route.textViewCarSeatAvaiable
import kotlinx.android.synthetic.main.activity_join_route.textViewDateRoute
import kotlinx.android.synthetic.main.activity_join_route.textViewDestinationReference
import kotlinx.android.synthetic.main.activity_join_route.textViewDestinationRoute
import kotlinx.android.synthetic.main.activity_join_route.textViewPickTimeRoute
import kotlinx.android.synthetic.main.activity_join_route.textViewPlace
import kotlinx.android.synthetic.main.activity_join_route.textViewTextUser

/**
 * JoinRouteActivity
 *
 * It has been created with the aim of having an authenticated passenger join an available route, via email
 * @author juan.rendon
 */

class JoinRouteActivity : AppCompatActivity() {

    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private val passengerAdapter = PassengerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_route)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Unirse a la Ruta"
        passenger_list.layoutManager = LinearLayoutManager(this)
        passenger_list.adapter = passengerAdapter
        val email = intent.getStringExtra(ID_USER)
        val idRoute = firebaseFirestore.collection("Route").document(email)
        idRoute.get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val route = document.toObject(Route::class.java) ?: Route()
                        bindRoute(route)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("Failed", "get failed with ", exception)
                }
        buttonSaveRoute.setOnClickListener {
            updateCarSeatRoutetoFirestore(email)
        }
    }

    private fun bindRoute(route: Route) {
        passengerAdapter.addAll(route.passengerName)
        textViewTextUser.text = route.driverName
        textViewDestinationRoute.text = route.destinationRoute
        textViewDateRoute.text = route.dateRoute
        textViewPickTimeRoute.text = route.timeRoute
        textViewCarSeatAvaiable.text = route.carSeat.toString()
        textViewPlace.text = route.meetingPlace
        textViewDestinationReference.text = route.destinationReference
    }

    private fun updateCarSeatRoutetoFirestore(email: String) {
        val idRoute = firebaseFirestore.collection("Route").document(email)
        idRoute.get().addOnSuccessListener { document ->
            if (document != null) {
                val carSeat = document.data?.get("carSeat") as Long
                val passengerList = document.data?.get("passengerName") as MutableList<String?>
                passengerList.add(email)
                idRoute.update(mapOf("carSeat" to carSeat - 1))
                idRoute.update(mapOf("passengerName" to passengerList))
                finish()
            }
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

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, JoinRouteActivity::class.java)
        }
    }

}
