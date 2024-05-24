package com.example.aplikace_rehabilitace.ui_.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentExercisePreparationBinding
import com.example.aplikace_rehabilitace.databinding.FragmentFinalPreparationBinding


class ExercisePreparationFragment : Fragment() {
    private lateinit var binding: FragmentExercisePreparationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return FragmentExercisePreparationBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentExercisePreparationBinding.bind(view)


    }

}