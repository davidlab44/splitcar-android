package com.globant.splitcar.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

/**
 * RouteRepository
 * This repository provides the data to the presentation layer
 *
 * @author david.mazo
 */

class RouteRepository {

    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private val allRoutesLiveData = MutableLiveData<List<Route>>()

    init {
        getAllInfo()
    }

    fun getAllRoutes() = allRoutesLiveData

    private fun getAllInfo() {
        firebaseFirestore.collection("Route")
                .get()
                .addOnSuccessListener { result ->
                    allRoutesLiveData.value = result.toObjects(Route::class.java)
                }
                .addOnFailureListener { exception ->
                    Log.d("error", "Error getting documents: ", exception)
                    allRoutesLiveData.value = emptyList()
                }
    }

    fun saveRoadReferences(placesList: MutableList<String>) {
        //TODO save placesList in firebase within current Route
    }
}
