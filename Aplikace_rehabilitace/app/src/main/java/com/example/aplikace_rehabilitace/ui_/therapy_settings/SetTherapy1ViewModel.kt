package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.app.Application
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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun addTherapySettings(patientId: Long){
        uiScope.launch {
            val therapy = getTherapy(patientId)
            if (therapy == null){
                val newTherapy = TherapySettings(patientId = patientId)
                insert(newTherapy)
            }


            /*
            val therapy = database.getTherapy(patientId)
            if (therapy == null) {
                    therapy = TherapySettings(patientId = patientId)
                    insert(therapy)
            }*/
        }
    }

    private suspend fun getTherapy(patientId: Long) {
        withContext(Dispatchers.IO){
            return@withContext database.getTherapy(patientId)
        }
    }

    private suspend fun insert(therapy: TherapySettings){
        withContext(Dispatchers.IO){
            database.insertTherapy(therapy)
        }
    }
}