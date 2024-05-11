package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aplikace_rehabilitace.database.VM2GDao

class SetTherapy4ViewModelFactory (
    private val dataSource: VM2GDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SetTherapy4ViewModel::class.java)) {
            return SetTherapy4ViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}