package com.globant.splitcar.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Constants
 *
 * In this class all the constants that are needed in the application are declared
 *
 * @author juan.rendon
 */

const val ID_USER = "idUser"
const val EMAIL = "email"
const val ROUTE_OBJECT = "Route"
const val ROUTE_ORIGIN = "Vizcaya"
val CARSEAT = arrayOf(1, 2, 3, 4)
val CURRENTTIME = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
