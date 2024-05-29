package com.example.aplikace_rehabilitace.ui_.exercise

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aplikace_rehabilitace.database.VM2GDao

class ExercisePreparationViewModelFactory (
    private val dataSource: VM2GDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExercisePreparationViewModel::class.java)) {
            return ExercisePreparationViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}