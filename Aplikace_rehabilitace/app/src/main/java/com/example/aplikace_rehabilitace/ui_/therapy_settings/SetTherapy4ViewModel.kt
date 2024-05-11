package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aplikace_rehabilitace.MainActivity
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.database.ExercisePosition
import com.example.aplikace_rehabilitace.database.Patient
import com.example.aplikace_rehabilitace.database.TherapySettings
import com.example.aplikace_rehabilitace.database.VM2GDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SetTherapy4ViewModel (
    val database: VM2GDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val positions = database.getAllExercisePositions()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



}