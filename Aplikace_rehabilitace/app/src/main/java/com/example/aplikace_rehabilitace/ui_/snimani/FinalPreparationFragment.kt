package com.example.aplikace_rehabilitace.ui_.snimani

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentFinalPreparationBinding
import com.example.aplikace_rehabilitace.databinding.FragmentPreparationRecording3Binding


class FinalPreparationFragment : Fragment() {
    private lateinit var binding: FragmentFinalPreparationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return FragmentFinalPreparationBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentFinalPreparationBinding.bind(view)
        binding.txtContinue.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_preparationRecording1_to_preparationRecording2))

    }
}