package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentSetTherapy2Binding

class SetTherapy2Fragment : Fragment() {

    //private lateinit var viewModel: SetTherapy2ViewModel
    private lateinit var binding: FragmentSetTherapy2Binding
    private var numOfExercise = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //viewModel = ViewModelProvider(this).get(SetTherapy1ViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_therapy2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSetTherapy2Binding.bind(view)
        binding.countRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = view.findViewById<RadioButton>(checkedId)
            numOfExercise = radioButton.text[0].digitToInt()
            //viewModel.setNumOfExercise(numOfExercise)
        }

        binding.continue1Button.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_setTherapy2Fragment_to_setTherapy3Fragment))
       //nefunkční: binding.continue1Button.setOnClickListener(findNavController().navigate(SetTherapy3FragmentDirections.actionRestart()))
    }


}