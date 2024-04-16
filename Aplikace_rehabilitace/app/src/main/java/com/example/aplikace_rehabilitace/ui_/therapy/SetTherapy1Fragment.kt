package com.example.aplikace_rehabilitace.ui_.therapy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentSetTherapy1Binding
import com.example.aplikace_rehabilitace.databinding.FragmentTherapyBinding

class SetTherapy1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_therapy1, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentSetTherapy1Binding.bind(view)

        binding.setTherapyButton.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_setTherapy1Fragment_to_setTherapy2Fragment))

    }
}