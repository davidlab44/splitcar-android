package com.globant.splitcar.model

import com.globant.splitcar.model.local.RoadReference

/**
 * Routes
 *
 * This is just a temp. hardcoded data sample, with a list of road reference
 *
 */

val roadReference = listOf(
        RoadReference("La ochenta"),
        RoadReference("San Juan"),
        RoadReference("Calle 10"),
        RoadReference("Avenida Guayabal"))

fun getRoadReferenceList(): List<RoadReference> {
    return roadReference
}