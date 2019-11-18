package com.globant.splitcar.model


import java.text.SimpleDateFormat
import java.util.Calendar

val currentDate = SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time)
val routes: ArrayList<Route> = ArrayList()
val route1 = Route(1,
        "phillip.coombs@globant.com",
        "City Plaza Envigado",
        "Vizcaya",
        currentDate,
        "17:00",
        "4",
        "")
val route2 = Route(2,
        "claudia.acevedo@globant.com",
        "Manrique Central",
        "Vizcaya",
        currentDate,
        "16:30",
        "4",
        "Av Regional, Parque norte, Jardín botánico, Est. Universidad UdeA, Campo Valdes, Manrique Central")
val route3 = Route(3,
        "s.mejia@globant.com",
        "UPB",
        "Vizcaya",
        currentDate,
        "19:00",
        "4",
        "Las palmas, Exposiciones, La 33 hasta la UPB (Laureles)")

fun addRoutes(route: Route) {
    routes.add(route)
}

fun addAllRoutes(): ArrayList<Route> {
    routes.add(route1)
    routes.add(route2)
    routes.add(route3)
    return routes
}
