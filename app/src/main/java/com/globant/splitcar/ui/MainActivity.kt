package com.globant.splitcar.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.globant.splitcar.R
import com.globant.splitcar.adapters.RouteListAdapter
import com.globant.splitcar.model.Route
import com.globant.splitcar.utils.ID_USER
import com.globant.splitcar.viewmodels.RouteViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.fabMakeRoute
import kotlinx.android.synthetic.main.content_main.recyclerViewRoutes
import kotlinx.android.synthetic.main.fragment_search.editTextSearch

class MainActivity : AppCompatActivity(), RouteEvents {

    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var routeListAdapter: RouteListAdapter
    private lateinit var routeViewModel: RouteViewModel

    companion object {
        private const val TAG = "KotlinActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        routeListAdapter = RouteListAdapter(this)
        recyclerViewRoutes.layoutManager = LinearLayoutManager(this)
        val mutableListRoute = mutableListOf<Route>()
        firebaseFirestore.collection("Route")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result.documents) {
                        val id = document.data?.get("id") as? Long
                        val driverName = document.data?.get("driverName") as String
                        val destinationRoute = document.data?.get("destinationRoute") as String
                        val originRoute = document.data?.get("originRoute") as String
                        val dateRoute = document.data?.get("dateRoute") as String
                        val timeRoute = document.data?.get("timeRoute") as String
                        val carSeat = document.data?.get("carSeat") as Long
                        val meetingPlace = document.data?.get("meetingPlace") as String
                        val destinationReference = document.data?.get("destinationReference") as String
                        val passengerName = document.data?.get("passengerName") as MutableList<String>
                        val route = id?.let {
                            Route(
                                    it,
                                    driverName,
                                    destinationRoute,
                                    originRoute,
                                    dateRoute,
                                    timeRoute,
                                    carSeat,
                                    destinationReference,
                                    meetingPlace,
                                    passengerName
                            )
                        }
                        if (route != null) {
                            mutableListRoute.add(route)
                        }
                    }
                    recyclerViewRoutes.adapter = routeListAdapter
                    routeListAdapter.addAll(mutableListRoute)
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
                }

        routeViewModel = ViewModelProviders.of(this).get(RouteViewModel::class.java)

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(cs: CharSequence, s: Int, b: Int, c: Int) {
                val result = editTextSearch.text.toString()
                if (result.isNotEmpty())
                    routeViewModel.filterByRouteReference(result)
                else
                    routeViewModel.getAllRoutes()
            }

            override fun afterTextChanged(editable: Editable) {}
            override fun beforeTextChanged(cs: CharSequence, i: Int, j: Int, k: Int) {}
        })

        fabMakeRoute.setOnClickListener {
            val email = intent.getStringExtra("email")
            val intent: Intent = RouteActivity.createIntent(this@MainActivity)
            intent.putExtra("email", email)
            startActivity(intent)
        }
    }

    override fun onItemClicked(route: Route) {
        val intent: Intent = JoinRouteActivity.createIntent(this@MainActivity)
        intent.putExtra(ID_USER, route.driverName)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        recyclerViewRoutes.layoutManager = LinearLayoutManager(this)
        val localList = mutableListOf<Route>()
        firebaseFirestore.collection("Route")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result.documents) {
                        val id = document.data?.get("id") as? Long
                        val driverName = document.data?.get("driverName") as String
                        val destinationRoute = document.data?.get("destinationRoute") as String
                        val originRoute = document.data?.get("originRoute") as String
                        val dateRoute = document.data?.get("dateRoute") as String
                        val timeRoute = document.data?.get("timeRoute") as String
                        val carSeat = document.data?.get("carSeat") as Long
                        val meetingPlace = document.data?.get("meetingPlace") as String
                        val destinationReference = document.data?.get("destinationReference") as String
                        val passengerName = document.data?.get("passengerName") as MutableList<String>
                        val route = id?.let {
                            Route(
                                    it,
                                    driverName,
                                    destinationRoute,
                                    originRoute,
                                    dateRoute,
                                    timeRoute,
                                    carSeat,
                                    destinationReference,
                                    meetingPlace,
                                    passengerName
                            )
                        }
                        if (route != null) {
                            localList.add(route)
                        }
                    }
                    recyclerViewRoutes.adapter = routeListAdapter

                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
                }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
