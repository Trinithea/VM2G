package com.example.aplikace_rehabilitace.ui_.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentExerciseChangePositionBinding
import com.example.aplikace_rehabilitace.databinding.FragmentFinalPreparationBinding

class ExerciseChangePositionFragment : Fragment() {
    private lateinit var binding: FragmentExerciseChangePositionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return FragmentExerciseChangePositionBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentExerciseChangePositionBinding.bind(view)
        binding.continueButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_exerciseChangePositionFragment_to_exerciseAfterFragment))
        binding.btnInfo.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_exerciseChangePositionFragment_to_exerciseInstructionsFragment))



    }
}