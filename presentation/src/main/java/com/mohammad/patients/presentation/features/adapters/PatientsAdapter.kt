package com.mohammad.patients.presentation.features.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.patients.domain.model.patients.PatientsRemoteModel
import com.mohammad.patients.presentation.databinding.RowPatientBinding

class PatientsAdapter(
    val onDeletePatient: (id: String) -> Unit,
    private val onClickItem: (id:String) -> Unit
) :
    ListAdapter<PatientsRemoteModel, PatientsAdapter.PatientsViewHolder>(DiffCallback) {

    var indexLastSelected=-1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {

        val binding=RowPatientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        val model=getItem(position)
        holder.bind(model, position)
    }


    inner class PatientsViewHolder(private val binding: RowPatientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: PatientsRemoteModel, position: Int) {
            binding.model=model

            binding.cardView.setOnClickListener {
                if (position != indexLastSelected) {

                    // check if the not default value
                    //notify the last selected item
                    if (indexLastSelected != -1) {
                        getItem(position).selected=false
                        notifyItemChanged(indexLastSelected)
                    }

                    //notify new item
                    indexLastSelected=position
                    getItem(position).selected=true
                    notifyItemChanged(position)
                }
                onClickItem(model.id)
            }
            binding.imageViewDelete.setOnClickListener {
                onDeletePatient(model.id)
            }
        }


    }

    private object DiffCallback : DiffUtil.ItemCallback<PatientsRemoteModel>() {
        override fun areItemsTheSame(
            oldItem: PatientsRemoteModel,
            newItem: PatientsRemoteModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PatientsRemoteModel,
            newItem: PatientsRemoteModel
        ): Boolean {
            return oldItem == newItem
        }

    }

}