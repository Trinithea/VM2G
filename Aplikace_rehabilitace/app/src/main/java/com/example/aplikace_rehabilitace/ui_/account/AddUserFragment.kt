package com.example.aplikace_rehabilitace.ui_.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.databinding.FragmentAddUserBinding
import com.example.aplikace_rehabilitace.databinding.FragmentWelcomeBinding


class AddUserFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentAddUserBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentAddUserBinding.bind(view)

        binding.addUserButton2.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_addUserFragment_to_homeScreenFragment))

    }

}