package com.example.aplikace_rehabilitace.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "therapy_positions_table",
    foreignKeys = [ForeignKey(
        entity = TherapySettings::class,
        parentColumns = ["therapyId"],
        childColumns = ["therapyId"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = ExercisePosition::class,
        parentColumns = ["positionId"],
        childColumns = ["positionId"],
        onDelete = ForeignKey.CASCADE
    )])
data class TherapyPositions(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "therapyPositionsId")
    var therapyPositionsId: Long = 0L,

    @ColumnInfo(name = "therapyId")
    val therapyId: Long,

    @ColumnInfo(name = "positionId")
    val positionId: Long,
)