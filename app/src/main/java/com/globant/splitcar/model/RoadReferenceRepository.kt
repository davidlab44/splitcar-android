package com.globant.splitcar.model

import android.app.Application
import androidx.lifecycle.LiveData
import com.globant.splitcar.model.local.RoadReference
import com.globant.splitcar.model.local.RoadReferenceDao
import com.globant.splitcar.model.local.RoadReferenceDatabase

/**
 * RoadReferenceRepository
 *
 * This repository provides the RoadReference data to the presentation layout
 *
 * @author david.mazo
 */

class RoadReferenceRepository(private val application: Application) {

    private var roadReferenceDao: RoadReferenceDao
    private var allRoadReferences: LiveData<List<RoadReference>>

    init {
        val database: RoadReferenceDatabase = RoadReferenceDatabase.getInstance(
                application.applicationContext
        )!!
        roadReferenceDao = database.roadReferenceDao()
        allRoadReferences = roadReferenceDao.getAllRoadReferences()
    }

    fun getRoadReferenceList(): List<RoadReference> {
        return RoadReferenceDatabase.getInstance(application.applicationContext)!!.roadReferenceDao().getRoadReferences()
    }

    fun getFilteredRoadReferenceList(roadReferenceHash: String): List<RoadReference> {
        return RoadReferenceDatabase.getInstance(application.applicationContext)!!.roadReferenceDao().getFilteredRoadReferences("%$roadReferenceHash%")
    }

    /*
    fun saveRoadReferenceList(roadReference: RoadReference) {
        RoadReferenceDatabase.getInstance(application.applicationContext)!!.roadReferenceDao().insert(roadReference)
    }
    */
}
