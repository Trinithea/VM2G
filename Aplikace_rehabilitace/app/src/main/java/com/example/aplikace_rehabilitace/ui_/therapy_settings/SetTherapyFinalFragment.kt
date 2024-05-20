package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentSetTherapyFinalBinding
import com.example.aplikace_rehabilitace.databinding.FragmentWelcomeBinding


class SetTherapyFinalFragment : Fragment() {
    private lateinit var binding: FragmentSetTherapyFinalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return FragmentSetTherapyFinalBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSetTherapyFinalBinding.bind(view)
        binding.txtContinue.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_setTherapyFinalFragment_to_homeScreenFragment))

    }

}