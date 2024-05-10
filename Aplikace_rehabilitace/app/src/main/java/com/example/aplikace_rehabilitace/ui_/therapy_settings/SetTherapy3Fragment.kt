package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aplikace_rehabilitace.database.VM2GDatabase
import com.example.aplikace_rehabilitace.databinding.FragmentSetTherapy3Binding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class SetTherapy3Fragment : Fragment() {

    private lateinit var viewModel: SetTherapy3ViewModel
    private lateinit var binding: FragmentSetTherapy3Binding
    private var numOfExercise = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, com.example.aplikace_rehabilitace.R.layout.fragment_set_therapy3, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = VM2GDatabase.getInstance(application).vm2gDatabaseDao
        val viewModelFactory = SetTherapy3ViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SetTherapy3ViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.setTherapy3ViewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.therapy.observe(viewLifecycleOwner, Observer { therapy ->
            if (therapy!= null) {
                numOfExercise = therapy.exerciseFrequency
                for (i in 1..numOfExercise) {
                    // Inflate the layout
                    val itemView = LayoutInflater.from(context).inflate(com.example.aplikace_rehabilitace.R.layout.item_time_of_exercise, null)

                    // Set data on the inflated view
                    // For example, assuming you have TextViews in item_time_of_exercise layout
                    //itemView.findViewById<TextView>(R.id.timeTextView).text = time

                    // Add the inflated view to the LinearLayout
                    binding.timeLinearLayout.addView(itemView)
                }
            }
        })



    }



}