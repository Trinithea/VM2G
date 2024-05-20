package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.view.forEachIndexed
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.database.VM2GDatabase
import com.example.aplikace_rehabilitace.databinding.FragmentSetTherapy3Binding
import com.example.aplikace_rehabilitace.databinding.FragmentSetTherapy6Binding
import com.google.android.material.textfield.TextInputEditText


class SetTherapy6Fragment : Fragment() {

    private lateinit var viewModel: SetTherapy6ViewModel
    private lateinit var binding: FragmentSetTherapy6Binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, com.example.aplikace_rehabilitace.R.layout.fragment_set_therapy6, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = VM2GDatabase.getInstance(application).vm2gDatabaseDao
        val viewModelFactory = SetTherapy6ViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SetTherapy6ViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.setTherapy6ViewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.continue6Button.setOnClickListener{
            viewModel.updateTherapySettings(binding.cbChciSeZapojit.isChecked)
            this.findNavController().navigate(R.id.action_setTherapy6Fragment_to_setTherapyFinalFragment)
        }

        viewModel.therapy.observe(viewLifecycleOwner, Observer { therapy ->
            if (therapy!= null) {
                binding.cbChciSeZapojit.isChecked = therapy.researchParticipation
            }
            }
        )


    }


}