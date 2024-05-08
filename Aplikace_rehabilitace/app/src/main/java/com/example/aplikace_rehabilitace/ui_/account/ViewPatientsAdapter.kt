package com.example.aplikace_rehabilitace.ui_.account

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.database.Patient
import com.example.aplikace_rehabilitace.databinding.ItemUserBinding

class ViewPatientsAdapter(val clickListener: PatientListener): ListAdapter<Patient, ViewPatientsAdapter.ViewHolder>(PatientDiffCallback()) {
    var data = listOf<Patient>()
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

    class ViewHolder private constructor(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Patient, clickListener: PatientListener){
            binding.patient = item // patient je variable z XML
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent:ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUserBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class PatientDiffCallback: DiffUtil.ItemCallback<Patient>(){
    override fun areItemsTheSame(oldItem: Patient, newItem: Patient): Boolean {
        return oldItem.patientId == newItem.patientId
    }

    override fun areContentsTheSame(oldItem: Patient, newItem: Patient): Boolean {
        return oldItem == newItem
    }
}

class PatientListener(val clickListener: (patientId: Long) -> Unit){
    fun onClick(patient:Patient) = clickListener(patient.patientId)
}