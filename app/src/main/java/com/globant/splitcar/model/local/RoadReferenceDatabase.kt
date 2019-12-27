package com.globant.splitcar.model.local

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * RoadReferenceDatabase
 * This class manage the local instances of Sqlite database
 *
 * @author david.mazo
 */

@Database(entities = [RoadReference::class], version = 1)
abstract class RoadReferenceDatabase : RoomDatabase() {
    abstract fun roadReferenceDao(): RoadReferenceDao

    companion object {
        private var instance: RoadReferenceDatabase? = null

        fun getInstance(context: Context): RoadReferenceDatabase? {
            if (instance == null) {
                synchronized(RoadReferenceDatabase::class) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            RoadReferenceDatabase::class.java, "route_database"
                    )
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                        .execute()
            }
        }
    }

    class PopulateDbAsyncTask(db: RoadReferenceDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val userDao = db?.roadReferenceDao()

        override fun doInBackground(vararg p0: Unit?) {
            userDao?.insert(RoadReference("Bello"))
            userDao?.insert(RoadReference("Niquia"))
            userDao?.insert(RoadReference("Copacabana"))
        }
    }


}