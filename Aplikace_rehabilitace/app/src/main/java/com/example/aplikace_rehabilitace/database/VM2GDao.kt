package com.example.aplikace_rehabilitace.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface VM2GDao {

    @Insert
    suspend fun insertTherapy(therapy: TherapySettings)

    @Update
    fun updateTherapy(therapy: TherapySettings)

    @Query("SELECT * from therapy_settings_table WHERE patientId = :patientId")
    fun getTherapy(patientId: Long): TherapySettings?

    @Insert
    suspend fun insertPatient(patient: Patient)

    @Update
    fun updatePatient(patient: Patient)

    @Query("SELECT * from patient_table WHERE patientId = :key")
    fun getPatient(key: Long): Patient?

    @Query("SELECT * FROM patient_table ORDER BY patientId")
    fun getAllPatients(): LiveData<List<Patient>>


}