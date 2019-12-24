package com.globant.splitcar.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.globant.splitcar.model.Route
import com.globant.splitcar.model.RouteRepository

/**
 * RouteViewModel
 * This view model to bind the data to the layout
 *
 * @author david.mazo
 */

class RouteViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: RouteRepository = RouteRepository()
    private var allRoutes: LiveData<List<Route>> = repository.getAllRoutes()
    private var filteredRoutes: MutableLiveData<List<Route>> = MutableLiveData()

    fun getAllRoutes(): LiveData<List<Route>> {
        return allRoutes
    }

    fun filterByRouteReference(destinationReference: String) {
        filteredRoutes.value = repository.getFilteredRoutes(destinationReference)
    }
}
