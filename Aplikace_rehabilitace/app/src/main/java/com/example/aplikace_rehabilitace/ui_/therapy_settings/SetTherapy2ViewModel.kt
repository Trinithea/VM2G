package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aplikace_rehabilitace.MainActivity
import com.example.aplikace_rehabilitace.database.Patient
import com.example.aplikace_rehabilitace.database.TherapySettings
import com.example.aplikace_rehabilitace.database.VM2GDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class SetTherapy2ViewModel (
    val database: VM2GDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var _therapy = MutableLiveData<TherapySettings?>()
    val therapy: LiveData<TherapySettings?>
        get() = _therapy

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    init{
        initializeTherapy()
    }

    private fun initializeTherapy(){
        uiScope.launch{
            _therapy.value = getTherapyFromDatabase(MainActivity.getCurrentPatientId())
           // Log.d("SetTherapy2", "id: ${therapy?.therapyId}, frequency: ${therapy?.exerciseFrequency}")
            //therapyFrequency.value = therapyL?.exerciseFrequency
        }
    }

    fun updateTherapySettings(patientId: Long, frequency: Int){
        uiScope.launch {
            update(patientId, frequency)
            _therapy.value = getTherapyFromDatabase(MainActivity.getCurrentPatientId())
        }
    }

    private suspend fun getTherapyFromDatabase(patientId: Long): TherapySettings?{
        return withContext(Dispatchers.IO) {
            val therapyL = database.getTherapy(patientId)
            therapyL
        }
    }

/*
    private suspend fun getTherapyFromDatabase(patientId: Long): TherapySettings?{
        return withContext(Dispatchers.IO){
            var therapy = database.getTherapy(patientId)
            therapy
        }
    }*/
    private suspend fun update(patientId: Long, frequency: Int) {
        withContext(Dispatchers.IO){
            database.updateTherapyFrequency(patientId, frequency)
        }
    }

}