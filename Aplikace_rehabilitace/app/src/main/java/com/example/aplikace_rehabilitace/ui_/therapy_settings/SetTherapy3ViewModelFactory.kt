package com.example.aplikace_rehabilitace.ui_.therapy_settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SetTherapy3ViewModelFactory (private val numOfExercise: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SetTherapy3ViewModel::class.java)) {
            return SetTherapy3ViewModel(numOfExercise) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}