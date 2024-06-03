package com.example.aplikace_rehabilitace.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patient_table")
data class Patient(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "patientId")
    var patientId: Long = 0L,

    @ColumnInfo(name = "name")
    val patientName: String,

    @ColumnInfo(name = "weight")
    var patientWeight: Int,

    @ColumnInfo(name = "birth")
    var patientBirth: String,

    @ColumnInfo(name = "accountId")
    var patientAccountId: Long

)
