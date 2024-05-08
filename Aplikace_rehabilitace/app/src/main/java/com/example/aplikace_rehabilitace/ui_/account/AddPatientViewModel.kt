package com.example.aplikace_rehabilitace.ui_.account

import android.app.Application
import android.util.Log
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aplikace_rehabilitace.database.Patient
import com.example.aplikace_rehabilitace.database.VM2GDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddPatientViewModel (
    val database: VM2GDao,
    application: Application
) : AndroidViewModel(application) {

    private var accountId: Long = -1L
    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var patient = MutableLiveData<Patient?>()

    private val _navigateToHomeScreen = MutableLiveData<Patient>()
    val navigationToHomeScreen: LiveData<Patient>
        get() = _navigateToHomeScreen
    fun doneNavigation(){
        //_navigateToHomeScreen.value = null
    }


    fun addPatient(name: String, weight: Int, age: Int){
        uiScope.launch {
            // TODO:předělat account ID

            val newPatient = Patient(patientName = name, patientWeight = weight, patientAge = age, patientAccountId = accountId)
            insert(newPatient)
            _navigateToHomeScreen.value = newPatient
        }
    }


    private suspend fun insert(patient: Patient){
        withContext(Dispatchers.IO){
            database.insertPatient(patient)
        }
    }
}