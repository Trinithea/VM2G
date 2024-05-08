package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aplikace_rehabilitace.database.VM2GDao

class SetTherapy1ViewModelFactory(
    private val dataSource: VM2GDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SetTherapy1ViewModel::class.java)) {
            return SetTherapy1ViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}