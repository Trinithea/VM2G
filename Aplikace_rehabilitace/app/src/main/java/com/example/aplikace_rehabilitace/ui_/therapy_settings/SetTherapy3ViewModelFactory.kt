package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aplikace_rehabilitace.database.VM2GDao
import com.example.aplikace_rehabilitace.ui_.account.AddPatientViewModel
/*
class SetTherapy3ViewModelFactory (
    private val dataSource: VM2GDao,
    private val application: Application
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(AddPatientViewModel::class.java)){
                return AddPatientViewModel(dataSource,application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}*/