package com.example.aplikace_rehabilitace.ui_.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.database.VM2GDatabase
import com.example.aplikace_rehabilitace.databinding.FragmentAddPatientBinding
import com.example.aplikace_rehabilitace.databinding.FragmentTherapyBinding


class AddPatientFragment : Fragment() {

    private lateinit var viewModel: AddPatientViewModel
    private lateinit var binding: FragmentAddPatientBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPatientBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = VM2GDatabase.getInstance(application).vm2gDatabaseDao
        val viewModelFactory = AddPatientViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AddPatientViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.addPatientViewModel = viewModel
/*
        viewModel.navigationToHomeScreen.observe(viewLifecycleOwner, Observer{
            patient -> patient?.let{
                this.findNavController().navigate(R.id.action_addPatientFragment_to_homeScreenFragment)
            viewModel.doneNavigation()
        }
        })
*/

        return FragmentAddPatientBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.addUserButton2.setOnClickListener{
            addPatient()
            this.findNavController().navigate(R.id.action_addPatientFragment_to_homeScreenFragment)
        }

    }

    private fun addPatient(){
        viewModel.addPatient(
            binding.nameTextInput.text.toString(),
            binding.weightTextInput.text.toString().toInt(),
            binding.ageTextInput.text.toString().toInt()
        )
        Toast.makeText(context, "Nový pacient pod tímto účtem byl úspěšně přidán", Toast.LENGTH_SHORT).show()
    }

}