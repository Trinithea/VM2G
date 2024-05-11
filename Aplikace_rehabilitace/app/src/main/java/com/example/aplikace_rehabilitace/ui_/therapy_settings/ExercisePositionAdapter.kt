package com.example.aplikace_rehabilitace.ui_.therapy_settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikace_rehabilitace.database.ExercisePosition
import com.example.aplikace_rehabilitace.database.TherapySettings
import com.example.aplikace_rehabilitace.databinding.ItemExercisePositionBinding
import com.example.aplikace_rehabilitace.databinding.ItemTimeOfExerciseBinding

class ExercisePositionAdapter (val clickListener: PositionListener): ListAdapter<ExercisePosition, ExercisePositionAdapter.ViewHolder>(PositionDiffCallback()) {
    var data = listOf<ExercisePosition>()
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

    class ViewHolder private constructor(val binding: ItemExercisePositionBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: ExercisePosition, clickListener: PositionListener){
            binding.position = item // patient je variable z XML
            binding.clickListener = clickListener

            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemExercisePositionBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class PositionDiffCallback: DiffUtil.ItemCallback<ExercisePosition>(){
    override fun areItemsTheSame(oldItem: ExercisePosition, newItem: ExercisePosition): Boolean {
        return oldItem.positionId == newItem.positionId
    }

    override fun areContentsTheSame(oldItem: ExercisePosition, newItem: ExercisePosition): Boolean {
        return oldItem == newItem
    }
}

class PositionListener(val clickListener: (positionId:Long) -> Unit){
    fun onClick(position:ExercisePosition) = clickListener(position.positionId)
}