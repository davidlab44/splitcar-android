package com.globant.splitcar.model

/**
 * Route
 *
 * contains the attributes to save the route lists
 * @author juan.rendon
 */

data class Route(
        var id: String = "",
        var driverName: String = "",
        var destinationRoute: String = "",
        var originRoute: String = "",
        var timeRoute: String = "",
        var carSeat: Int = 0,
        var destinationReference: MutableList<String> = mutableListOf(),
        var meetingPlace: String = "",
        var passengerName: MutableList<String> = mutableListOf()
)
