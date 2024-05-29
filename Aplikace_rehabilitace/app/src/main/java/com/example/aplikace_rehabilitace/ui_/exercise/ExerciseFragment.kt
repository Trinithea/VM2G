package com.example.aplikace_rehabilitace.ui_.exercise

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.database.ExercisePosition
import com.example.aplikace_rehabilitace.database.VM2GDatabase
import com.example.aplikace_rehabilitace.databinding.FragmentExerciseBinding


class ExerciseFragment : Fragment() {
    enum class State {
        INSTRUCTIONS, TIMER, CHANGE
    }

    private var state = State.INSTRUCTIONS

    private fun changeState(){
        val nextStateIndex = (state.ordinal + 1) % State.entries.size
        state = State.entries[nextStateIndex]
    }

    private lateinit var binding: FragmentExerciseBinding
    private lateinit var viewModel: ExerciseViewModel
    private lateinit var positionList: List<ExercisePosition>

    private var currentPosition = 0

    private lateinit var handler: Handler
    private var runnable: Runnable? = null
    private var startTime = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = VM2GDatabase.getInstance(application).vm2gDatabaseDao
        val viewModelFactory = ExerciseViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ExerciseViewModel::class.java)
        binding = FragmentExerciseBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        handler = Handler(Looper.getMainLooper())
        return binding.root
    }

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel.positions.observe(viewLifecycleOwner, Observer {
            it?.let{
                Log.d("ST5","positionList initialized")
                positionList = it
                //initializePosition(positionList[currentPosition])
                initializeInstructions(positionList[currentPosition])
            }
        })

        binding.continueButton.setOnClickListener {
            changeState()
            var position = positionList[currentPosition]
            when (state){
                State.INSTRUCTIONS -> {
                    initializeInstructions(position)
                }
                State.TIMER -> {
                    //TODO: txt2: obsah, txtTimeLeft: obsah -> spustit odpočet

                    binding.btnPreparation.visibility = View.GONE
                    binding.btnInfo.visibility = View.VISIBLE
                    binding.imgTimer.visibility = View.VISIBLE
                    binding.txtTimeLeft.visibility = View.VISIBLE
                    binding.txt2.setTextColor(R.color.colorBlue)
                    binding.txt1.text = "${currentPosition+1}/${positionList.size}"
                    binding.continueButton.setBackgroundResource(R.drawable.roundcorner32_gray)
                    binding.continueButton.isClickable = false
                    startTime = System.currentTimeMillis() / 1000
                    Log.d("Timer", "startTime: ${startTime}")
                    startTimer()
                }
                State.CHANGE -> {
                    binding.txtTimeLeft.text = "00:00"
                    currentPosition++
                    if (currentPosition < positionList.size){
                        binding.txt1.text = "Změňte na polohu ${positionList[currentPosition].positionName}"
                        binding.continueButton.text = getString(com.example.aplikace_rehabilitace.R.string.zmenit_polohu)
                    }
                    else{
                        binding.txt1.text = ""
                        binding.continueButton.text = "Konec cvičení"
                        binding.continueButton.setOnClickListener {
                            this.findNavController().navigate(R.id.action_exerciseFragment_to_exerciseAfterFragment)
                        }
                    }

                }
            }
        }

        binding.btnInfo.setOnClickListener {
            //TODO
        }
        binding.btnPreparation.setOnClickListener {
            //TODO
            this.findNavController().navigate(R.id.action_exerciseFragment_to_exercisePreparationFragment)
        }
    }

    private fun startTimer() {

        runnable = object : Runnable {
            override fun run() {
                // Calculate remaining time
                val currentTime = System.currentTimeMillis() / 1000
                Log.d("Timer","currentTime: ${currentTime}")
                val elapsedTime = currentTime - startTime // Assume startTime is defined elsewhere
                Log.d("Timer","elapsedTime: ${elapsedTime}")
                val remainingTime = 20 - elapsedTime // TODO 5 minutes in seconds (300)
                Log.d("Timer","remainingTime: ${remainingTime}")

                // Update txtTimeLeft
                val minutes = remainingTime / 60
                val seconds = remainingTime % 60
                Log.d("Timer","výpis: ${String.format("%02d:%02d", minutes, seconds)}")
                binding.txtTimeLeft.text = String.format("%02d:%02d", minutes, seconds)

                // Check if the timer should stop
                if (remainingTime <= 0) {
                    stopTimer()
                    // Perform any actions required upon completion of the timer
                } else {
                    // Continue the timer
                    handler.postDelayed(this, 1000)
                }
            }
        }
        handler.post(runnable!!)
    }

    private fun stopTimer() {
        handler.removeCallbacks(runnable!!)
        runnable = null
        binding.txtTimeLeft.text = "00:00"
        binding.continueButton.setBackgroundResource(R.drawable.roundcorner32)
        binding.continueButton.isClickable = true
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    private fun initializeInstructions(position:ExercisePosition){
        binding.imgPositions.setImageResource(position.positionImage)
        binding.txt1.text = "${currentPosition+1}/${positionList.size} / 5 minut (${positionList.size*5} minut celkem)\n${position.positionName}"
        binding.txt2.text = position.positionDescription
        binding.continueButton.text = getString(R.string.pokracovat)
        binding.txt2.setTextColor(R.color.colorDarkGray)
        binding.btnPreparation.visibility = View.VISIBLE
        binding.imgTimer.visibility = View.GONE
        binding.btnInfo.visibility = View.GONE
        binding.txtTimeLeft.visibility = View.GONE
    }

}