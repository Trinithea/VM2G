package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.aplikace_rehabilitace.MainActivity
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.database.VM2GDatabase
import com.example.aplikace_rehabilitace.databinding.FragmentAddPatientBinding
import com.example.aplikace_rehabilitace.databinding.FragmentSetTherapy1Binding
import com.example.aplikace_rehabilitace.databinding.FragmentTherapyBinding
import com.example.aplikace_rehabilitace.ui_.account.AddPatientViewModel
import com.example.aplikace_rehabilitace.ui_.account.AddPatientViewModelFactory

class SetTherapy1Fragment : Fragment() {

    private lateinit var viewModel: SetTherapy1ViewModel
    private lateinit var binding: FragmentSetTherapy1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("SetTherapy1","SetTherapy1 onCreateView")
        //binding = FragmentSetTherapy1Binding.inflate(inflater, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_therapy1, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = VM2GDatabase.getInstance(application).vm2gDatabaseDao
        val viewModelFactory = SetTherapy1ViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SetTherapy1ViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.setTherapy1ViewModel = viewModel


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       binding.setTherapyButton.setOnClickListener {
            addTherapy()
            //Toast.makeText(context, "Jdeme nastavovat", Toast.LENGTH_SHORT).show()
            this.findNavController().navigate(R.id.action_setTherapy1Fragment_to_setTherapy2Fragment)
        }

    }

    private fun addTherapy(){
        viewModel.addTherapySettings(MainActivity.getCurrentPatientId())

    }
}