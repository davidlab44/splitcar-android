package com.globant.splitcar.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.globant.splitcar.model.Route
import com.globant.splitcar.model.RouteRepository

class RouteViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: RouteRepository = RouteRepository(application)
    private var allRoutes: List<Route> = repository.getAllRoutes()
    private var filteredUsers: MutableLiveData<List<Route>> = MutableLiveData()

    fun getAllRoutes(): List<Route> {
        return allRoutes
    }

    fun filterByName(destinationReference: String) {
        filteredUsers.value = repository.getFilteredRoutes(destinationReference)
    }
}