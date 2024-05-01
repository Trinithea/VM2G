package com.example.aplikace_rehabilitace.ui_.account

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.aplikace_rehabilitace.database.VM2GDao
import com.example.aplikace_rehabilitace.database.VM2GDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class TherapyHomeScreenViewModel(
    val database: VM2GDao,
    application: Application
    ) : AndroidViewModel(application) {
        private var viewModelJob = Job()

        override fun onCleared() {
            super.onCleared()
            viewModelJob.cancel()
        }

        private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    
    }