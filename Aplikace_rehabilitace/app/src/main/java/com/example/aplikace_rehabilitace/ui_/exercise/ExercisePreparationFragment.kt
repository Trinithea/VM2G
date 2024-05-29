package com.example.aplikace_rehabilitace.ui_.exercise

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.database.ExercisePosition
import com.example.aplikace_rehabilitace.database.VM2GDatabase
import com.example.aplikace_rehabilitace.databinding.FragmentExercisePreparationBinding
import com.example.aplikace_rehabilitace.databinding.FragmentFinalPreparationBinding
import com.example.aplikace_rehabilitace.ui_.therapy_settings.SetTherapy1ViewModel
import com.example.aplikace_rehabilitace.ui_.therapy_settings.SetTherapy1ViewModelFactory


class ExercisePreparationFragment : Fragment() {
    private lateinit var binding: FragmentExercisePreparationBinding
    private lateinit var viewModel: ExercisePreparationViewModel
    private lateinit var positionList: List<ExercisePosition>
    private lateinit var steps: List<String>
    private lateinit var images: List<String>
    private var numOfSteps = -1
    private var currentStep = -1
    private var currentPosition = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val application = requireNotNull(this.activity).application
        val dataSource = VM2GDatabase.getInstance(application).vm2gDatabaseDao
        val viewModelFactory = ExercisePreparationViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ExercisePreparationViewModel::class.java)
        //viewModel.positionScopeCompleted.await()
        binding = FragmentExercisePreparationBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        Log.d("ST5", "onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("ST5", "${viewModel.positions.value}")
        viewModel.positions.observe(viewLifecycleOwner, Observer {
            it?.let{
                Log.d("ST5","positionList initialized")
                positionList = it
                initializePosition(positionList[0])
                currentStep += 1
                nextStep(positionList[0])
            }
        })
        Log.d("ST5", "onViewCreated")

        binding.backButton.setOnClickListener{
            if(currentStep <= 0){
                this.findNavController().navigate(R.id.action_exercisePreparationFragment_to_startExerciseFragment)
            }
            else{
                binding.continueButton.text = getString(R.string.pokracovat)
                currentStep -= 1
                nextStep(positionList[currentPosition])
            }

        }
        binding.continueButton.setOnClickListener{
            //if (currentPosition+1 < positionList.size){
                if (currentStep == numOfSteps - 2){
                    //currentStep = -1
                    //currentPosition++
                    //initializePosition(positionList[currentPosition])
                    //nextStep(positionList[currentPosition])
                    binding.continueButton.text = "Jdeme cvičit!"
                    currentStep += 1
                    nextStep(positionList[currentPosition])
                }
                else if(currentStep == numOfSteps-1){
                    this.findNavController().navigate(R.id.action_exercisePreparationFragment_to_exerciseFragment)
                }
                else{
                    currentStep += 1
                    nextStep(positionList[currentPosition])
                }

        }

    }
    private fun initializePosition(position:ExercisePosition){
        steps = position.positionPreparationSteps.split('\n')
        numOfSteps = steps.size
        images = position.positionPreparationImages.split(';')
        Log.d("ImageResource", images.toString())
    }



    private fun nextStep(position:ExercisePosition){

        val imageNameWithoutExtension = images[currentStep].substringBefore(".")
        Log.d("ImageResource", imageNameWithoutExtension)
        val resourceId = resources.getIdentifier(imageNameWithoutExtension, "drawable", "com.example.aplikace_rehabilitace")


        if (resourceId!= 0) {
            binding.imgMain.setImageResource(resourceId)
        } else {
            Log.e("ImageResource", "Resource ID not found")
        }
        binding.txtStepsCounter.text = "${currentStep+1}/${numOfSteps}"
        binding.txtStepDescription.text = steps[currentStep]

    }

}