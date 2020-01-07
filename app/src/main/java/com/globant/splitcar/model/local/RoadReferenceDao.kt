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

    @Query("SELECT * FROM road_reference_table WHERE name LIKE :roadReferenceHash LIMIT 10")
    fun getFilteredRoadReferences(roadReferenceHash: String): List<RoadReference>

    @Query("SELECT * FROM road_reference_table ORDER BY name DESC LIMIT 10")
    fun getRoadReferences(): List<RoadReference>

}