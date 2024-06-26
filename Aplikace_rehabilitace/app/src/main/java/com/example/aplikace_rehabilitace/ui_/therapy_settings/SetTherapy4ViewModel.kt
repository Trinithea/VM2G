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
import com.example.aplikace_rehabilitace.database.TherapyPositions
import com.example.aplikace_rehabilitace.database.TherapySettings
import com.example.aplikace_rehabilitace.database.VM2GDao
import kotlinx.coroutines.CompletableDeferred
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

    private val removeScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val addScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val removeScopeCompleted = CompletableDeferred<Unit>()

    init {
       Log.d("ST4", "positions: ${positions}")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun addPositionToTherapy(positionId: Long){
        addScope.launch {
            removeScopeCompleted.await()
            val therapy = getTherapyFromDatabase(MainActivity.getCurrentPatientId())
            val therapyPosition = TherapyPositions(therapyId = therapy?.therapyId, positionId = positionId)
            insert(therapyPosition)

        }
    }

    fun removeAllPositionsForTherapy(){
        removeScope.launch {
            val therapy = getTherapyFromDatabase(MainActivity.getCurrentPatientId())
            Log.d("ST4","removeAllPositionsForTherapy")
            remove(therapy?.therapyId)
            removeScopeCompleted.complete(Unit)
        }
    }

    private suspend fun remove(therapyId:Long?){
        withContext(Dispatchers.IO){
            database.removeAllPositionsForTherapy(therapyId)
        }
    }

    private suspend fun getTherapyFromDatabase(patientId: Long): TherapySettings?{
        return withContext(Dispatchers.IO) {
            val therapyL = database.getTherapy(patientId)
            therapyL
        }
    }

    private suspend fun insert(therapyPosition: TherapyPositions){
        withContext(Dispatchers.IO){
            database.insertTherapyPosition(therapyPosition)
            Log.d("ST4","${therapyPosition.therapyId}, ${therapyPosition.positionId}")
        }
    }

}