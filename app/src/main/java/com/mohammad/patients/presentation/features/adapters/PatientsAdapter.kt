package com.mohammad.patients.presentation.features.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.patients.databinding.RowPatientBinding
import com.mohammad.patients.domain.model.patients.PatientsRemoteModel

class PatientsAdapter(private val patients : List<PatientsRemoteModel>) : RecyclerView.Adapter<PatientsAdapter.PatientsViewHolder>() {

    var indexLastSelected=-1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val binding=RowPatientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        val model=patients[position]
        holder.bind(model, position)

    }

    override fun getItemCount(): Int {
        return patients.size
    }

    inner class PatientsViewHolder(private val binding: RowPatientBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(model: PatientsRemoteModel, position: Int) {
            binding.model = model

            binding.cardView.setOnClickListener {
                if (position != indexLastSelected){

                    // check if the not default value
                    //notify the last selected item
                    if (indexLastSelected != -1){
                        patients[indexLastSelected].selected = false
                        notifyItemChanged(indexLastSelected)
                    }

                    //notify new item
                    indexLastSelected = position
                    patients[position].selected = true
                    notifyItemChanged(position)


                }
            }
        }





    }

}