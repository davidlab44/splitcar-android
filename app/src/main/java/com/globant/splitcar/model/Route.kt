package com.globant.splitcar.model

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
    var passengerName : MutableList<String>
)
