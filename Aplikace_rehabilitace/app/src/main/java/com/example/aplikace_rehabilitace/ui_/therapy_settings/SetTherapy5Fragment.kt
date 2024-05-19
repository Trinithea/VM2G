package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.database.VM2GDatabase
import com.example.aplikace_rehabilitace.databinding.FragmentSetTherapy4Binding
import com.example.aplikace_rehabilitace.databinding.FragmentSetTherapy5Binding

class SetTherapy5Fragment : Fragment() {

    private lateinit var viewModel: SetTherapy5ViewModel
    private lateinit var binding: FragmentSetTherapy5Binding
    private lateinit var adapter: ExercisePositionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetTherapy5Binding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = VM2GDatabase.getInstance(application).vm2gDatabaseDao

        val viewModelFactory = SetTherapy5ViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SetTherapy5ViewModel::class.java)



        binding.setLifecycleOwner(this)
        binding.setTherapy5ViewModel = viewModel

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_therapy, container, false)
        adapter = ExercisePositionAdapter(PositionListener {
                positionId -> selectPosition(positionId)
        })
        var pos = viewModel.positions
        binding.positionSelectedList.adapter = adapter
        viewModel.positions.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.data = it
            }
        })

        return binding.root
    }

    fun selectPosition(positionId: Long){

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.continue4Button.setOnClickListener {
            this.findNavController().navigate(R.id.action_setTherapy5Fragment_to_setTherapy6Fragment)
        }
    }


}