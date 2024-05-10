package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aplikace_rehabilitace.database.Patient
import com.example.aplikace_rehabilitace.database.TherapySettings
import com.example.aplikace_rehabilitace.database.VM2GDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SetTherapy1ViewModel (
    val database: VM2GDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private var therapy = MutableLiveData<TherapySettings?>()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun addTherapySettings(patientId: Long){
        uiScope.launch {
            val therapy = getTherapyFromDatabase(patientId)
            if (therapy == null){
                val newTherapy = TherapySettings(patientId = patientId)
                insert(newTherapy)
            }

        }
    }

    private suspend fun getTherapyFromDatabase(patientId: Long): TherapySettings?{
        return withContext(Dispatchers.IO){
            var therapy = database.getTherapy(patientId)
            therapy
        }
    }


    private suspend fun insert(therapy: TherapySettings){
        withContext(Dispatchers.IO){
            database.insertTherapy(therapy)
        }
    }
}