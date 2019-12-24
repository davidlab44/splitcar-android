package com.globant.splitcar.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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

    fun getAllRoutes(): LiveData<List<Route>> {
        return allRoutes
    }
}
