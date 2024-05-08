package com.example.aplikace_rehabilitace.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "therapy_settings_table",
        foreignKeys = [ForeignKey(
            entity = Patient::class,
            parentColumns = ["patientId"],
            childColumns = ["patientId"],
            onDelete = ForeignKey.CASCADE
        )])
data class TherapySettings(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "therapyId")
    var therapyId: Long = 0L,

    @ColumnInfo(name = "patientId")
    val patientId: Long,

    @ColumnInfo(name = "frequency")
    val exerciseFrequency: Int = 1,

    @ColumnInfo(name = "time1")
    val exercise1Time: String = "00:00",

    @ColumnInfo(name = "time2")
    val exercise2Time: String = "00:00",

    @ColumnInfo(name = "time3")
    val exercise3Time: String = "00:00",

    @ColumnInfo(name = "time4")
    val exercise4Time: String = "00:00",

    @ColumnInfo(name = "time5")
    val exercise5Time: String = "00:00",

    @ColumnInfo(name = "research")
    val researchParticipation: Boolean = false



)
