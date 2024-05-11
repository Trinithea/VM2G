package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TimePicker
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.core.view.forEachIndexed
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.database.VM2GDatabase
import com.example.aplikace_rehabilitace.databinding.FragmentSetTherapy3Binding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class SetTherapy3Fragment : Fragment() {

    private lateinit var viewModel: SetTherapy3ViewModel
    private lateinit var binding: FragmentSetTherapy3Binding
    private var numOfExercise = 1
   // private var times = mutableStateListOf(listOf<String?>())
    //private lateinit var times : List<String?>
   private lateinit var times: MutableList<String?>

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
        binding.buttonTempDB.setOnClickListener{
            viewModel.addTemporaryExercisePositions()
        }

        viewModel.therapy.observe(viewLifecycleOwner, Observer { therapy ->
            if (therapy!= null) {
                binding.timeLinearLayout.removeAllViews()
                numOfExercise = therapy.exerciseFrequency
                times = viewModel.getTimes().toMutableList()
                //times = listOf("21:30","12:15","15:15","13:30","14:20")
                for (i in 1..numOfExercise) {

                    // Inflate the layout
                    val itemView = LayoutInflater.from(context).inflate(com.example.aplikace_rehabilitace.R.layout.item_time_of_exercise, null)
                    val editText = itemView.findViewById<TextInputEditText>(com.example.aplikace_rehabilitace.R.id.timeTextInputView)
                    // Set data on the inflated view
                    // For example, assuming you have TextViews in item_time_of_exercise layout
                    //itemView.findViewById<TextInputLayout>(R.id.textInputLayout).findViewById<TextInputEditText>(R.id.timeTextInputView).text = times[i]
                    editText.setText(times[i-1])
                    editText.inputType = InputType.TYPE_NULL
                    editText.setOnClickListener { openDialog(editText) }

                    val deleteButton = itemView.findViewById<ImageButton>(com.example.aplikace_rehabilitace.R.id.deleteViewButton)
                    deleteButton.setOnClickListener {
                        binding.timeLinearLayout.removeView(itemView)
                    }


                    // Add the inflated view to the LinearLayout
                    binding.timeLinearLayout.addView(itemView)
                }
            }
        })

        binding.continue2Button.setOnClickListener{
            updateTherapy()
            this.findNavController().navigate(R.id.action_setTherapy3Fragment_to_setTherapy4Fragment)

        }

    }


    private fun updateTherapy() {
        numOfExercise = binding.timeLinearLayout.childCount

        binding.timeLinearLayout.forEachIndexed { index, view ->
            val editText = view.findViewById<TextInputEditText>(com.example.aplikace_rehabilitace.R.id.timeTextInputView)
            Log.d("SetTherapy3", "index: ${index}, time${editText.text.toString()}")
            times[index] = editText.text.toString()
        }
        for (i in 0..4-numOfExercise){
            Log.d("SetTherapy3", "index: ${i}, time: 00:00")
            times[4-i] = "00:00"
        }
        viewModel.updateTherapySettings(numOfExercise, times)
    }



    private fun openDialog(editText: TextInputEditText){
        val dialog = TimePickerDialog(context, R.style.TimeDialogTheme,
            { view, hourOfDay, minute ->
                val hourOfDayText = if (hourOfDay < 10) "0${hourOfDay}" else hourOfDay.toString()
                val minuteText = if (minute < 10) "0${minute}" else minute.toString()
                editText.setText("$hourOfDayText:$minuteText")
            }, 7,0, true)
        dialog.show()
    }

}