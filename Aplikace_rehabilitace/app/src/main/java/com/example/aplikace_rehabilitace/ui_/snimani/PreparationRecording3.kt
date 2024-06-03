package com.example.aplikace_rehabilitace.ui_.snimani

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentPreparationRecording3Binding

class PreparationRecording3 : Fragment() {
    private lateinit var binding: FragmentPreparationRecording3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return FragmentPreparationRecording3Binding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPreparationRecording3Binding.bind(view)
        binding.continueButton.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_preparationRecording3_to_finalPreparationFragment))
        binding.backButton.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_preparationRecording3_to_preparationRecording2))
    
    }

}