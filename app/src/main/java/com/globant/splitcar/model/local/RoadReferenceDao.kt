package com.globant.splitcar.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * RoadReferenceDatabase
 * This is the DAO to deal with RoadReference objects within local database
 *
 * @author david.mazo
 */

@Dao
interface RoadReferenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roadReference: RoadReference)

    @Update
    fun update(roadReference: RoadReference)

    @Delete
    fun delete(roadReference: RoadReference)

    @Query("DELETE FROM road_reference_table")
    fun deleteAllRoadReferences()

    @Query("SELECT * FROM road_reference_table ORDER BY name DESC")
    fun getAllRoadReferences(): LiveData<List<RoadReference>>

    @Query("SELECT * FROM road_reference_table WHERE name LIKE :roadReferenceHash ORDER BY name DESC LIMIT 10")
    fun getFilteredRoadReferences(roadReferenceHash: String): List<RoadReference>

    @Query("SELECT * FROM road_reference_table ORDER BY name DESC LIMIT 10")
    fun getRoadReferences(): List<RoadReference>

    @Query("UPDATE road_reference_table SET isRoadReference = 1 WHERE name LIKE :roadReferenceName ")
    fun updateRoadReferenceSelectedField(roadReferenceName: String)

    @Query("SELECT * FROM road_reference_table WHERE isRoadReference = 1")
    fun getRoadReferencesSelected(): List<RoadReference>

    @Query("UPDATE road_reference_table SET isRoadReference = 0 WHERE isRoadReference = 1 ")
    fun rollBackRoadReferenceSelected()

    @Query("UPDATE road_reference_table SET isRoadReference = 0 WHERE name LIKE :roadReferenceToUnselect ")
    fun unselectRoadReference(roadReferenceToUnselect: String)

    @Query("UPDATE road_reference_table SET isDestinationReference = 1 WHERE name LIKE :roadReferenceName ")
    fun setDestinationReferenceSelected(roadReferenceName: String)

    @Query("UPDATE road_reference_table SET isDestinationReference = 0 WHERE isDestinationReference = 1 ")
    fun rollBackDestinationReferenceSelected()

    @Query("SELECT * FROM road_reference_table WHERE isDestinationReference = 1")
    fun getDestinationReference(): List<RoadReference>
}