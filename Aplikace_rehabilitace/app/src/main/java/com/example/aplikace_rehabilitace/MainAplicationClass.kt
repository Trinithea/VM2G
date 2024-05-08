package com.example.aplikace_rehabilitace

import android.app.Application

class MainApplicationClass : Application() {

    companion object{
        private var currentPatientId: Long = -1L //TODO: dát lepší default value a zpracovat ji dál
        fun getCurrentPatientId() : Long {
            return currentPatientId
        }
        fun setCurrentPatientId(id:Long){
            currentPatientId = id
        }
    }
}