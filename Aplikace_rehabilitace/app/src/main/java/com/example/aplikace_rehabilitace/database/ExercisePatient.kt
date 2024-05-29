package com.example.aplikace_rehabilitace.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "exercise_patient_table",
    foreignKeys = [ForeignKey(
        entity = Patient::class,
        parentColumns = ["patientId"],
        childColumns = ["patientId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ExercisePatient(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "expatId")
    var expatId: Long = 0L,

    @ColumnInfo(name = "patientId")
    val patientId: Long?,

    @ColumnInfo(name = "dateTime")
    val dateTime: String

)
