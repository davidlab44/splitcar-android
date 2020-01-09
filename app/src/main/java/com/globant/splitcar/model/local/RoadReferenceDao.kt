package com.globant.splitcar.model.local

import androidx.lifecycle.LiveData
import androidx.room.*

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

    @Query("UPDATE road_reference_table SET selected = 1 WHERE name LIKE :roadReferenceName ")
    fun updateRoadReferenceSelectedField(roadReferenceName: String)

    @Query("SELECT * FROM road_reference_table WHERE selected = 1")
    fun getRoadReferencesSelected(): List<RoadReference>

    @Query("UPDATE road_reference_table SET selected = 0 WHERE selected = 1 ")
    fun rollBackRoadReferenceSelected()

    @Query("UPDATE road_reference_table SET selected = 0 WHERE name LIKE :roadReferenceToUnselect ")
    fun unselectRoadReference(roadReferenceToUnselect: String)



}