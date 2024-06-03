package com.example.aplikace_rehabilitace.database

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "position_table")
data class ExercisePosition(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "positionId")
    var positionId: Long = 0L,

    @ColumnInfo(name = "name")
    val positionName: String,

    @ColumnInfo(name = "stimulation")
    var positionStimulation: String,

    @ColumnInfo(name = "description")
    var positionDescription: String,

    @ColumnInfo(name = "preparationSteps")
    var positionPreparationSteps: String,

    @ColumnInfo(name = "preparationImages")
    var positionPreparationImages: String,

    @ColumnInfo(name = "imageName")
    var positionImage: String

)
