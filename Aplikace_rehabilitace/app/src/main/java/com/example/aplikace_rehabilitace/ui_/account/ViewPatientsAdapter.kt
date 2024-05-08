package com.example.aplikace_rehabilitace.ui_.account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.compose.ui.text.capitalize
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikace_rehabilitace.R
import com.example.aplikace_rehabilitace.TextItemViewHolder
import com.example.aplikace_rehabilitace.database.Patient

class ViewPatientsAdapter: RecyclerView.Adapter<ViewPatientsAdapter.ViewHolder>() {
    var data = listOf<Patient>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val firstLetterButton : Button = itemView.findViewById(R.id.firstLetterButton)
        val patientNameTextView: TextView = itemView.findViewById(R.id.patientNameTextView)

        fun bind(item: Patient, data: List<Patient>){
            firstLetterButton.text = item.patientName[0].uppercase()
            patientNameTextView.text = item.patientName.replaceFirstChar(Char::titlecase)
            val index = data.indexOf(item) % 6
            firstLetterButton.setBackgroundResource(when(index){
                0 -> R.drawable.round_purple_32
                1 -> R.drawable.round_wine_32
                2 -> R.drawable.round_lightblue_32
                3 -> R.drawable.round_pink_32
                4 -> R.drawable.round_greenblue_32
                5 -> R.drawable.round_brown_32
                else -> R.drawable.round_purple_32
            })
        }
    }
}