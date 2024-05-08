package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentSetTherapy3Binding

class SetTherapy3Fragment : Fragment() {

    private lateinit var viewModel: SetTherapy3ViewModel
    private lateinit var binding: FragmentSetTherapy3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       //viewModelFactory = SetTherapy3ViewModelFactory(SetTherapy3FragmentArgs.fromBundle(arguments).numOf)
      // viewModel = ViewModelProvider(this, viewModelFactory).get(SetTherapy3ViewModel::class.java)
       // viewModel = ViewModelProvider(this).get(SetTherapy2ViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_therapy3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSetTherapy3Binding.bind(view)

       // binding.textView.text = viewModel.numOfExercisesPerDay.value.toString()

    }

}