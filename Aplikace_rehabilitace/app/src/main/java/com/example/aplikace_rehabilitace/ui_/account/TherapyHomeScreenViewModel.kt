package com.example.aplikace_rehabilitace.ui_.account

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplikace_rehabilitace.MainActivity
import com.example.aplikace_rehabilitace.database.Patient
import com.example.aplikace_rehabilitace.database.TherapySettings
import com.example.aplikace_rehabilitace.database.VM2GDao
import com.example.aplikace_rehabilitace.database.VM2GDatabase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        private var patient = MutableLiveData<Patient?>()
        val patients = database.getAllPatients()
        private var _therapy = MutableLiveData<TherapySettings?>()
        val therapy: LiveData<TherapySettings?>
            get() = _therapy
        var initializingCompleted = CompletableDeferred<Unit>()

        init{
            initializePatient()
        }



        private fun initializePatient(){
            uiScope.launch{
                patient.value = getPatientFromDatabase()
            }
        }

        private suspend fun getPatientFromDatabase(): Patient?{
            return withContext(Dispatchers.IO){
                var patient = database.getPatient(1)
                patient
            }
        }

        fun initializeTherapy(){
            uiScope.launch{
                _therapy.value = getTherapyFromDatabase(MainActivity.getCurrentPatientId())
                Log.d("HomeScreen", "initializeTherapy, therapy: ${_therapy.value}")
                initializingCompleted.complete(Unit)
            }
        }

        private suspend fun getTherapyFromDatabase(patientId: Long): TherapySettings?{
            return withContext(Dispatchers.IO) {
                val therapyL = database.getTherapy(patientId)
                therapyL
            }
        }
    }