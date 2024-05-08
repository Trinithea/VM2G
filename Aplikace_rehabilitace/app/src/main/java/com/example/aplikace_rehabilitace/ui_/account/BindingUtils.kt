package com.example.aplikace_rehabilitace.ui_.account

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.database.Patient

@BindingAdapter("patientName")
fun TextView.setPatientName(item: Patient?){
    item?.let{
        text = item.patientName.replaceFirstChar(Char::titlecase)
    }
}

@BindingAdapter("patientFirstLetter")
fun TextView.setPatientFirstLetterTextView(item: Patient?){
    item?.let{
        text = item.patientName[0].uppercase()
        val index = item.patientId % 6
        setBackgroundResource(when(index){
            0L -> R.drawable.round_purple_32
            1L -> R.drawable.round_wine_32
            2L -> R.drawable.round_lightblue_32
            3L -> R.drawable.round_pink_32
            4L -> R.drawable.round_greenblue_32
            5L -> R.drawable.round_brown_32
            else -> R.drawable.round_purple_32
        })

    }
}