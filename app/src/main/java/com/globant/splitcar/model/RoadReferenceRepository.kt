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

    //TODO eliminar los assert null
    fun getRoadReferenceList(): List<RoadReference> {
        return RoadReferenceDatabase.getInstance(application.applicationContext)!!.roadReferenceDao().getRoadReferences()
    }

    //TODO eliminar los assert null
    fun getFilteredRoadReferenceList(roadReferenceHash: String): List<RoadReference> {
        return RoadReferenceDatabase.getInstance(application.applicationContext)!!.roadReferenceDao().getFilteredRoadReferences("%$roadReferenceHash%")
    }

    //TODO eliminar los assert null
    fun updateRoadReferenceSelectedField(roadReferenceName: String) {
        RoadReferenceDatabase.getInstance(application.applicationContext)!!.roadReferenceDao().updateRoadReferenceSelectedField(roadReferenceName)
    }

    //TODO eliminar los assert null
    fun getRoadReferencesSelected(): List<RoadReference> {
        return RoadReferenceDatabase.getInstance(application.applicationContext)!!.roadReferenceDao().getRoadReferencesSelected()
    }

    //TODO eliminar los assert null
    fun rollBackRoadReferenceSelected() {
        RoadReferenceDatabase.getInstance(application.applicationContext)!!.roadReferenceDao().rollBackRoadReferenceSelected()
    }

    //TODO eliminar los assert null
    fun unselectRoadReference(roadReferenceToUnselect: String) {
        RoadReferenceDatabase.getInstance(application.applicationContext)!!.roadReferenceDao()
            .unselectRoadReference(roadReferenceToUnselect)
    }


    /*
    fun saveRoadReferenceList(roadReference: RoadReference) {
        RoadReferenceDatabase.getInstance(application.applicationContext)!!.roadReferenceDao().insert(roadReference)
    }
    */
}
