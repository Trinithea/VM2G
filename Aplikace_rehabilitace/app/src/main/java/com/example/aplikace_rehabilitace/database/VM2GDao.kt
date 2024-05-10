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

    @Query("UPDATE therapy_settings_table SET frequency = :frequency WHERE patientId = :patientId")
    fun updateTherapyFrequency(patientId: Long, frequency: Int)

    @Query("UPDATE therapy_settings_table SET frequency = :frequency, time1 = :time1, time2 = :time2, time3=:time3, time4=:time4, time5=:time5 WHERE patientId = :patientId")
    fun updateTherapyTimes(patientId: Long, frequency: Int, time1:String, time2:String, time3:String,time4:String,time5:String)


}