package com.example.aplikace_rehabilitace.ui_

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentAddPatientBinding
import com.example.aplikace_rehabilitace.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return FragmentWelcomeBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentWelcomeBinding.bind(view)

        binding.addUserButton.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_addUserFragment))
        binding.startTherapyButton.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_homeScreenFragment))
    }

}