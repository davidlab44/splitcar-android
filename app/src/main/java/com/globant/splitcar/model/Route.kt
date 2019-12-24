package com.globant.splitcar.model

/**
 * Route
 *
 * contains the attributes to save the route lists
 * @author juan.rendon
 */

data class Route(
        var id: Long,
        var driverName: String,
        var destinationRoute: String,
        var originRoute: String,
        var dateRoute: String,
        var timeRoute: String,
        var carSeat: Long,
        var destinationReference: String,
        var meetingPlace: String,
        var passengerName: MutableList<String>
)
