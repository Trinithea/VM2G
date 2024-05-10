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

class SetTherapy3ViewModel (
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
        }
    }

    fun updateTherapySettings(frequency: Int, times: List<String?>){
        uiScope.launch {
            update(MainActivity.getCurrentPatientId(),
                frequency,
                times[0].toString(),
                times[1].toString(),
                times[2].toString(),
                times[3].toString(),
                times[4].toString())
            _therapy.value = getTherapyFromDatabase(MainActivity.getCurrentPatientId())
        }
    }

    fun getTimes(): List<String?>{
        val times = listOf(_therapy.value?.exercise1Time,
            _therapy.value?.exercise2Time,
            _therapy.value?.exercise3Time,
            _therapy.value?.exercise4Time,
            _therapy.value?.exercise5Time)
        return times
    }

    private suspend fun getTherapyFromDatabase(patientId: Long): TherapySettings?{
        return withContext(Dispatchers.IO) {
            val therapyL = database.getTherapy(patientId)
            therapyL
        }
    }
    private suspend fun update(patientId: Long, frequency: Int, time1:String, time2:String, time3: String, time4:String, time5:String) {
        withContext(Dispatchers.IO){
            database.updateTherapyTimes(patientId, frequency, time1, time2, time3, time4, time5)
        }
    }

}