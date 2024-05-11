package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.app.Application
import android.util.Log
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

    fun addTemporaryExercisePositions() {
        val pos1 = ExercisePosition(
            positionName = "Cvičení na zádech",
            positionStimulation = "Stimulace zón záhlavní a čelistní",
            positionDescription = "Přítlak provádějte pod bradou a na spodní straně týlu hlavy",
            positionPreparationSteps = "Připravte lehátko, umístěte zpola nafouklý disk, ležící podložku a pro větší komfort cvičícího podlavník\n" +
                    "Umístěte 3 míčky velikosti 3 pod pravé žebro a na obě třísla\n"+
                    "Umístěte 4 míčky velikosti 2 na kotník z obou stran na nárt a plosku nohy",
            positionPreparationImages = "1_1.jpeg;1_2.jpeg;1_3.jpeg",
            positionImage = R.drawable.pos1
        )

        val pos2 = ExercisePosition(
            positionName = "Cvičení na boku",
            positionStimulation = "Stimulace zón ramenní a čelistní na boku",
            positionDescription = "Přítlak provádějte na vnitřní straně kolena a na rameni",
            positionPreparationSteps = "empty",
            positionPreparationImages = "empty",
            positionImage = R.drawable.pos2
        )

        val pos3 = ExercisePosition(
            positionName = "Poloha na zádech: RO I",
            positionStimulation = "empty",
            positionDescription = "empty",
            positionPreparationSteps = "empty",
            positionPreparationImages = "empty",
            positionImage = R.drawable.pos3
        )

        addTemporaryExercisePosition(pos1)
        addTemporaryExercisePosition(pos2)
        addTemporaryExercisePosition(pos3)

    }


    private fun addTemporaryExercisePosition(position: ExercisePosition){
        uiScope.launch {
            insert(position)
        }
    }


    private suspend fun insert(position: ExercisePosition){
        withContext(Dispatchers.IO){
            database.insertExercisePosition(position)
        }
    }

}