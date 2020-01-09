package com.globant.splitcar.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * RoadReference
 *
 * Data class for RoadReference objects
 *
 * @author david.mazo
 */

@Entity(tableName = "road_reference_table")
data class RoadReference(var name: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var isRoadReference: Boolean = false
    var isDestinationReference: Boolean = false

    override fun toString(): String {
        return name
    }
}
