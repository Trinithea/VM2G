package com.example.aplikace_rehabilitace.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Patient::class, TherapySettings::class, ExercisePosition::class, TherapyPositions::class, ExercisePatient::class],
    version = 8,
    exportSchema = false)
abstract class VM2GDatabase : RoomDatabase() {
    abstract val vm2gDatabaseDao: VM2GDao

    companion object {

        @Volatile
        private var instance: VM2GDatabase? = null

        fun getInstance(context: Context): VM2GDatabase {
            synchronized(this) {
                var instance = this.instance

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        VM2GDatabase::class.java,
                        "vm2g_database"
                    ).fallbackToDestructiveMigration().build()
                    this.instance = instance
                }

                return instance
            }
        }
    }
}