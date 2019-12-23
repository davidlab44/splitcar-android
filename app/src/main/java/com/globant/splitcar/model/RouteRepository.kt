package com.globant.splitcar.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class RouteRepository {

    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private val allRoutesLiveData = MutableLiveData<List<Route>>()

    init {
        getAllInfo()
    }

    fun getAllRoutes() = allRoutesLiveData

    private fun getAllInfo() {
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
                allRoutesLiveData.value = mutableListRoute
            }
            .addOnFailureListener { exception ->
                Log.d("error", "Error getting documents: ", exception)
                allRoutesLiveData.value = emptyList()
            }
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
        val routeProof = Route(
            4, "phillip.coombs@globant.com", "City Plaza Envigado", "Vizcaya",
            currentDate, "17:00", 4, "Las vegas", "S2", mutableListOf()
        )
        mutableListRoute.add(routeProof)
        return mutableListRoute
    }
}