package com.example.aplikace_rehabilitace.ui_.exercise

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.aplikace_rehabilitace.MainActivity
import com.example.aplikace_rehabilitace.database.ExercisePosition
import com.example.aplikace_rehabilitace.database.TherapyPositions
import com.example.aplikace_rehabilitace.database.TherapySettings
import com.example.aplikace_rehabilitace.database.VM2GDao
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExerciseViewModel (
    val database: VM2GDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _positionIds = MutableLiveData<List<Long?>>()
    val positionIds: LiveData<List<Long>> get() = _positionIds.map { it?.filterNotNull()?: emptyList() }
    //val positions: LiveData<List<ExercisePosition?>> get() = getAllSelectedPositions().map { it.toList() }

    // LiveData to hold the selected positions
    private val _positions = MutableLiveData<List<ExercisePosition>>()
    val positions: LiveData<List<ExercisePosition>> get() = _positions
    private val therapyScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val positionIdsScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val positionsScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val positionIdsCompleted = CompletableDeferred<Unit>()
    private val therapyInitCompleted = CompletableDeferred<Unit>()
    val positionScopeCompleted = CompletableDeferred<Unit>()
    val therapyPositionsSync: MediatorLiveData<List<TherapyPositions>> = MediatorLiveData()

    private val mediatorLiveData = MediatorLiveData<List<ExercisePosition>>()
    private var _therapy = MutableLiveData<TherapySettings?>()
    val therapy: LiveData<TherapySettings?>
        get() = _therapy

    init {
        initializeTherapy()
        getPositionIds()
        fetchAllSelectedPositions()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    private fun initializeTherapy(){
        therapyScope.launch{
            Log.d("ST5","initializeTherapy")
            _therapy.value = getTherapyFromDatabase(MainActivity.getCurrentPatientId())
            therapyInitCompleted.complete(Unit)
        }
    }

    private fun getPositionIds(){
        positionIdsScope.launch {
            therapyInitCompleted.await()
            Log.d("ST5", "getPositionIds")
            val therapy = getTherapyFromDatabase(MainActivity.getCurrentPatientId())
            Log.d("ST5", "therapyID ${therapy?.therapyId}")
            // Convert LiveData<List<Long?>> to List<Long?>
            val therapyPositions = getAllTherapyPositionsFromDatabase(therapy?.therapyId)
            Log.d("ST5", "therapyPositionsL ${therapyPositions}")
//.value?: emptyList()
            val positionIdsL= mutableListOf<Long?>()
            val size =  therapyPositions.size?: 0
            Log.d("ST5", "size ${size}")
            therapyPositions.forEach { therapyPosition ->
                Log.d("ST5", "positionId ${therapyPosition.positionId}")
                positionIdsL.add(therapyPosition.positionId)

            }
            Log.d("ST5", "positionIdsL ${positionIdsL}")
            // Filter out null values if needed
            val filteredPositionIds = positionIdsL.filterNotNull()

            // Post the filtered list to _positionIds
            _positionIds.postValue(filteredPositionIds)
            positionIdsCompleted.complete(Unit)
        }
    }


    private suspend fun getAllTherapyPositionsFromDatabase(therapyId:Long?): List<TherapyPositions>{
        return withContext(Dispatchers.IO){
            val therapyPositionsL = database.getAllTherapyPositions(therapyId)
            Log.d("ST5", "therapyPositionsL ${therapyPositionsL}")
            therapyPositionsL
        }
    }

    private fun fetchAllSelectedPositions() {
        // getPositionIds()
        positionsScope.launch {
            positionIdsCompleted.await()
            Log.d("ST5", "fetchAllSelectedPositions")
            Log.d("ST5", "positionIds ${positionIds.value}")
            val selectedPositions= mutableListOf<ExercisePosition>()
            positionIds.value?.forEach { positionId ->

                val position = getPositionFromDatabase(positionId)
                Log.d("ST5", "${position?.positionName}")
                if (position!= null) {
                    selectedPositions.add(position)
                }

            }
            _positions.postValue(selectedPositions)
            positionScopeCompleted.complete(Unit)
        }
    }



    private suspend fun getTherapyFromDatabase(patientId: Long): TherapySettings?{
        return withContext(Dispatchers.IO) {
            val therapyL = database.getTherapy(patientId)
            therapyL
        }
    }

    private suspend fun getPositionFromDatabase(positionId: Long): ExercisePosition? {
        return withContext(Dispatchers.IO) {
            val positionL = database.getExercisePosition(positionId)
            positionL
        }
    }


}