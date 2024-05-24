package com.example.aplikace_rehabilitace.ui_.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentFinalPreparationBinding
import com.example.aplikace_rehabilitace.databinding.FragmentStartExerciseBinding

class StartExerciseFragment : Fragment() {
    private lateinit var binding: FragmentStartExerciseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return FragmentStartExerciseBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentStartExerciseBinding.bind(view)
        binding.btnStartTherapy.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_startExerciseFragment_to_exerciseInstructionsFragment))
        binding.btnShowPreparation.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_startExerciseFragment_to_exercisePreparationFragment))
    }
}