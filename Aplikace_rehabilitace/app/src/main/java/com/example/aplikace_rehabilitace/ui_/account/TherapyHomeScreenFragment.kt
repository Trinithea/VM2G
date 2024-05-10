package com.example.aplikace_rehabilitace.ui_.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aplikace_rehabilitace.MainActivity
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.database.VM2GDatabase
import com.example.aplikace_rehabilitace.databinding.FragmentTherapyBinding
import com.example.aplikace_rehabilitace.databinding.ItemUserBinding


class TherapyHomeScreenFragment : Fragment() {

    private lateinit var viewModel: TherapyHomeScreenViewModel
    private lateinit var binding: FragmentTherapyBinding
    private lateinit var adapter: ViewPatientsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTherapyBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = VM2GDatabase.getInstance(application).vm2gDatabaseDao
        val viewModelFactory = TherapyHomeScreenViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TherapyHomeScreenViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.therapyHomeScreenViewModel = viewModel

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_therapy, container, false)

        val manager = GridLayoutManager(activity,3)
        binding.patientList.layoutManager = manager

        adapter = ViewPatientsAdapter(PatientListener {
            patientId -> logPatient(patientId)
        })
        binding.patientList.adapter = adapter
        viewModel.patients.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.data = it
            }
        })

        //viewModel.database.getPatient(patientId) -> pak to začne padat

        return binding.root
    }

    private fun logPatient(patientId: Long){
        MainActivity.setCurrentPatientId(patientId)
        binding.btnStartTherapy.isEnabled = true
        binding.btnStartTherapy.setBackgroundResource(R.drawable.roundcorner32)
        viewModel.patients.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.data = it
            }
        })
        //Toast.makeText(context,"Byl zvolen pacient ${MainActivity.getCurrentPatientId()}", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnStartTherapy.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_homeScreenFragment_to_setTherapy1Fragment))
        binding.btnAddNewPatient.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_homeScreenFragment_to_addPatientFragment))
    }

}