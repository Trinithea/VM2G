package com.example.aplikace_rehabilitace.ui_.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentExerciseAfterBinding
import com.example.aplikace_rehabilitace.databinding.FragmentFinalPreparationBinding

class ExerciseAfterFragment : Fragment() {
    private lateinit var binding: FragmentExerciseAfterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return FragmentExerciseAfterBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentExerciseAfterBinding.bind(view)
        binding.btnHomeScreen.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_exerciseAfterFragment_to_homeScreenFragment))
        binding.btnMessage.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_exerciseAfterFragment_to_exerciseAfterMessageFragment))

    }
}