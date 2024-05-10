package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.aplikace_rehabilitace.MainActivity
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.database.VM2GDatabase
import com.example.aplikace_rehabilitace.databinding.FragmentSetTherapy2Binding

class SetTherapy2Fragment : Fragment() {

    //private lateinit var viewModel: SetTherapy2ViewModel
    private lateinit var binding: FragmentSetTherapy2Binding
    private lateinit var viewModel: SetTherapy2ViewModel
    private var numOfExercise = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_therapy2, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = VM2GDatabase.getInstance(application).vm2gDatabaseDao
        val viewModelFactory = SetTherapy2ViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SetTherapy2ViewModel::class.java)


        binding.setLifecycleOwner(this)
        binding.setTherapy2ViewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.therapy.observe(viewLifecycleOwner, Observer { therapy ->
            if (therapy!= null) {
                // Check the RadioButton based on therapy.exerciseFrequency
                when (therapy.exerciseFrequency) {
                    1 -> binding.rb1.isChecked = true
                    2 -> binding.rb2.isChecked = true
                    3 -> binding.rb3.isChecked = true
                    4 -> binding.rb4.isChecked = true
                    5 -> binding.rb5.isChecked = true
                }
            }
        })

        binding.countRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = view.findViewById<RadioButton>(checkedId)
            numOfExercise = radioButton.text[0].digitToInt()
            //viewModel.setNumOfExercise(numOfExercise)
        }

        binding.continue1Button.setOnClickListener{
            updateFrequency()
            this.findNavController().navigate(R.id.action_setTherapy2Fragment_to_setTherapy3Fragment)
        }

        //binding.continue1Button.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_setTherapy2Fragment_to_setTherapy3Fragment))
       //nefunkční: binding.continue1Button.setOnClickListener(findNavController().navigate(SetTherapy3FragmentDirections.actionRestart()))

    }

    private fun updateFrequency(){
        viewModel.updateTherapySettings(MainActivity.getCurrentPatientId(), numOfExercise)

    }




}