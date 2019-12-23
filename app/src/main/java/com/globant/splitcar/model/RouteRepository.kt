package com.globant.splitcar.model

import android.app.Application
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class RouteRepository(private val application: Application) {

    private val firebaseFirestore = FirebaseFirestore.getInstance()

    //TODO return a LiveData<List<Route>> object instead the current List<Route>
    fun getAllRoutes(): List<Route> {
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
            }
            .addOnFailureListener { exception ->
                Log.d("error", "Error getting documents: ", exception)
            }
        return mutableListRoute
    }

    fun getFilteredRoutes(destinationReference: String): List<Route> {
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
            }
            .addOnFailureListener { exception ->
                Log.d("error", "Error getting documents: ", exception)
            }
        return mutableListRoute
    }

}