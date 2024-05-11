package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.aplikace_rehabilitace.database.ExercisePosition

@BindingAdapter("positionName")
fun TextView.setPositionName(item: ExercisePosition?){
    item?.let{
        text = item.positionName
    }
}