package com.example.aplikace_rehabilitace.ui_.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.database.VM2GDatabase
import com.example.aplikace_rehabilitace.databinding.FragmentTherapyBinding


class TherapyHomeScreenFragment : Fragment() {

    private lateinit var viewModel: TherapyHomeScreenViewModel
    private lateinit var binding: FragmentTherapyBinding
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


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //val binding =
        //binding.btnStartTherapy.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_homeScreenFragment_to_setTherapy1Fragment))

       // viewModel = ViewModelProvider(this).get(TherapyHomeScreenViewModel::class.java)

        //binding.lifeCycleOwner = this
    }

}