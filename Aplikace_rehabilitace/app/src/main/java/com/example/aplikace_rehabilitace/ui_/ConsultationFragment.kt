package com.example.aplikace_rehabilitace.ui_

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentAddPatientBinding
import com.example.aplikace_rehabilitace.databinding.FragmentConsultationBinding


class ConsultationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consultation, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentConsultationBinding.bind(view)

        binding.btnStartRecordingExercise.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_homeScreenFragment_to_sendRequestFragment))

    }

}