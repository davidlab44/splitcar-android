package com.globant.splitcar.model

data class Route(
        var id: Int,
        var driverName: String,
        var destinationRoute: String,
        var originRoute: String,
        var dateRoute: String,
        var timeRoute: String,
        var carSeat: Int,
        var destinationReference: String)
