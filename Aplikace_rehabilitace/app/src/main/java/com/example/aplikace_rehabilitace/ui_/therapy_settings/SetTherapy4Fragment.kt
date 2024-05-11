package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aplikace_rehabilitace.MainActivity
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.database.VM2GDatabase
import com.example.aplikace_rehabilitace.databinding.FragmentSetTherapy4Binding
import com.example.aplikace_rehabilitace.databinding.FragmentTherapyBinding
import com.example.aplikace_rehabilitace.ui_.account.PatientListener
import com.example.aplikace_rehabilitace.ui_.account.TherapyHomeScreenViewModel
import com.example.aplikace_rehabilitace.ui_.account.TherapyHomeScreenViewModelFactory
import com.example.aplikace_rehabilitace.ui_.account.ViewPatientsAdapter
import kotlin.math.E


class SetTherapy4Fragment : Fragment(){
    private lateinit var viewModel: SetTherapy4ViewModel
    private lateinit var binding: FragmentSetTherapy4Binding
    private lateinit var adapter: ExercisePositionAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetTherapy4Binding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = VM2GDatabase.getInstance(application).vm2gDatabaseDao
        val viewModelFactory = SetTherapy4ViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SetTherapy4ViewModel::class.java)


       binding.setLifecycleOwner(this)
       binding.setTherapy4ViewModel = viewModel

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_therapy, container, false)


        adapter = ExercisePositionAdapter(PositionListener {
                positionId -> selectPosition(positionId)
        })
        binding.positionList.adapter = adapter
        viewModel.positions.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.data = it
            }
        })

        return binding.root
    }

    private fun selectPosition(positionId: Long){
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.continue3Button.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_setTherapy4Fragment_to_setTherapy5Fragment))
    }
}