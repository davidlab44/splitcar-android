package com.globant.splitcar.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.globant.splitcar.R
import com.globant.splitcar.adapters.RoutesAdapter
import com.globant.splitcar.model.Route
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.fabMakeRoute
import kotlinx.android.synthetic.main.content_main.recyclerViewRoutes


class MainActivity : AppCompatActivity() {
    private val firebaseFirestore = FirebaseFirestore.getInstance()

    companion object {
        private const val TAG = "KotlinActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                        val destinationReference = document.data?.get("destinationReference") as String
                        val route = id?.let { Route(it, driverName, destinationRoute, originRoute, dateRoute, timeRoute, carSeat, destinationReference) }
                        if (route != null) {
                            localList.add(route)
                        }
                    }
                    recyclerViewRoutes.adapter = RoutesAdapter(localList, this)
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
                }


//         recyclerViewRoutes.adapter = RoutesAdapter(addAllRoutes(), this)
        fabMakeRoute.setOnClickListener {
            val userName = intent.getStringExtra("userName")
            val intent: Intent = RouteActivity.createIntent(this@MainActivity)
            intent.putExtra("userName", userName)
            startActivity(intent)
        }
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
                        val destinationReference = document.data?.get("destinationReference") as String
                        val route = id?.let { Route(it, driverName, destinationRoute, originRoute, dateRoute, timeRoute, carSeat, destinationReference) }
                        if (route != null) {
                            localList.add(route)
                        }
                    }
                    recyclerViewRoutes.adapter = RoutesAdapter(localList, this)
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
