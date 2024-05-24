package com.example.aplikace_rehabilitace.ui_.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentExerciseInstructionsBinding
import com.example.aplikace_rehabilitace.databinding.FragmentFinalPreparationBinding

class ExerciseInstructionsFragment : Fragment() {
    private lateinit var binding: FragmentExerciseInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return FragmentExerciseInstructionsBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentExerciseInstructionsBinding.bind(view)
        binding.continueButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_exerciseInstructionsFragment_to_exerciseTimerFragment))
        binding.btnPreparation.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_exerciseInstructionsFragment_to_exercisePreparationFragment))

    }
}