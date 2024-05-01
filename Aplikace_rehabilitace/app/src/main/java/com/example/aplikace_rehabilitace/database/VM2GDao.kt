package com.example.aplikace_rehabilitace.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface VM2GDao {

    @Insert
    fun insert(therapy: TherapySettings)

    @Update
    fun update(therapy: TherapySettings)

    @Query("SELECT * from therapy_settings_table WHERE patientId = :patientId")
    fun getTherapy(patientId: Long): TherapySettings?

    @Insert
    fun insert(patient: Patient)

    @Update
    fun update(patient: Patient)

    @Query("SELECT * from patient_table WHERE patientId = :key")
    fun getPatient(key: Long): Patient?

    @Query("SELECT name FROM patient_table ORDER BY patientId DESC")
    fun getAllPatients(): LiveData<List<Patient>>


}