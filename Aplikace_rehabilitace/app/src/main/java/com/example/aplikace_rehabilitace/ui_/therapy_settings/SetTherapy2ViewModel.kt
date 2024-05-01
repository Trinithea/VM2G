package com.example.aplikace_rehabilitace.ui_.therapy_settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SetTherapy2ViewModel : ViewModel() {
    private val _numOfExercisesPerDay = MutableLiveData<Int>()
    val numOfExercisesPerDay : LiveData<Int>
        get() = _numOfExercisesPerDay

    fun setNumOfExercise(count: Int){
        _numOfExercisesPerDay.value = count
    }

    init{
        _numOfExercisesPerDay.value=1
    }


}