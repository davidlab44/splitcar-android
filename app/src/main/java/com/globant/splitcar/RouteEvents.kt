package com.globant.splitcar

import com.globant.splitcar.model.Route

interface RouteEvents {
    fun onItemClicked(route: Route)
}
