package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikace_rehabilitace.database.Patient
import com.example.aplikace_rehabilitace.database.TherapySettings
import com.example.aplikace_rehabilitace.databinding.ItemTimeOfExerciseBinding
import com.example.aplikace_rehabilitace.databinding.ItemUserBinding
import com.example.aplikace_rehabilitace.ui_.account.ViewPatientsAdapter

class ViewTimesOfTherapyAdapter (val clickListener: TherapyListener): ListAdapter<String, ViewTimesOfTherapyAdapter.ViewHolder>(TherapyDiffCallback()) {
    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, clickListener)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ItemTimeOfExerciseBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: String, clickListener: TherapyListener){
            //binding.timeText = item // patient je variable z XML
            //binding.clickListener = clickListener

            //binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTimeOfExerciseBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class TherapyDiffCallback: DiffUtil.ItemCallback<String>(){
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}

class TherapyListener(val clickListener: () -> Unit){
    fun onClick(therapy:TherapySettings) = clickListener()
}