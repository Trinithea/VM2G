package com.example.aplikace_rehabilitace.ui_.snimani

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentPreparationRecording1Binding
import com.example.aplikace_rehabilitace.databinding.FragmentSetTherapyFinalBinding

class PreparationRecording1 : Fragment() {
    private lateinit var binding: FragmentPreparationRecording1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return FragmentPreparationRecording1Binding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPreparationRecording1Binding.bind(view)
        binding.continueButton.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_preparationRecording1_to_preparationRecording2))
        binding.backButton.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_preparationRecording1_to_homeScreenFragment))

    }

}