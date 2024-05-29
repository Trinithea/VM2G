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

    @Insert
    suspend fun insertExercisePosition(position: ExercisePosition)

    @Insert
    suspend fun insertExercisePatient(expat: ExercisePatient)

    @Insert
    suspend fun insertPatient(patient: Patient)

    @Insert
    suspend fun insertTherapyPosition(therapyPositions: TherapyPositions)

    @Update
    fun updateTherapy(therapy: TherapySettings)

    @Query("UPDATE therapy_settings_table SET frequency = :frequency, time1 = :time1, time2 = :time2, time3=:time3, time4=:time4, time5=:time5 WHERE patientId = :patientId")
    fun updateTherapyTimes(patientId: Long, frequency: Int, time1:String, time2:String, time3:String,time4:String,time5:String)

    @Query("UPDATE therapy_settings_table SET research=:research WHERE patientId = :patientId")
    fun updateTherapyResearch(patientId: Long, research: Boolean)

    @Query("SELECT * from therapy_settings_table WHERE patientId = :patientId")
    fun getTherapy(patientId: Long): TherapySettings?

    @Query("SELECT * from exercise_patient_table WHERE patientId = :patientId")
    fun getAllCompletedExercise(patientId: Long): LiveData<List<ExercisePatient>>

    @Update
    fun updatePatient(patient: Patient)

    @Query("SELECT * from patient_table WHERE patientId = :key")
    fun getPatient(key: Long): Patient?

    @Query("SELECT * FROM patient_table ORDER BY patientId")
    fun getAllPatients(): LiveData<List<Patient>>

    @Query("SELECT * FROM position_table WHERE positionId = :positionId")
    fun getExercisePosition(positionId:Long): ExercisePosition

    @Query("SELECT * FROM position_table ORDER BY positionId")
    fun getAllExercisePositions(): LiveData<List<ExercisePosition>>

    @Query("SELECT * FROM therapy_positions_table WHERE therapyId=:therapyId")
    suspend fun getAllTherapyPositions(therapyId: Long?): List<TherapyPositions>

    @Query("DELETE FROM therapy_positions_table WHERE therapyId = :therapyId")
    fun removeAllPositionsForTherapy(therapyId: Long?)

    @Query("UPDATE therapy_settings_table SET frequency = :frequency WHERE patientId = :patientId")
    fun updateTherapyFrequency(patientId: Long, frequency: Int)




}