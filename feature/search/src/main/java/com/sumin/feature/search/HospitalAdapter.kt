package com.sumin.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sumin.feature.search.databinding.ItemHospitalBinding
import java.lang.IllegalStateException

class HospitalAdapter(
    private val onItemClick: (String) -> Unit
): PagingDataAdapter<HospitalItemUiState, RecyclerView.ViewHolder>(UiModelComparator) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if(holder is HospitalViewHolder) {
            holder.bind(item as HospitalItemUiState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HospitalViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return when(peek(position)) {
            is HospitalItemUiState -> R.layout.item_hospital
            else -> throw IllegalStateException("Unknown View")
        }
    }

    inner class HospitalViewHolder(private val binding: ItemHospitalBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(hospitalItem: HospitalItemUiState) {
            binding.hospitalItem = hospitalItem
            binding.root.setOnClickListener { onItemClick(hospitalItem.id) }
        }
    }

    object UiModelComparator : DiffUtil.ItemCallback<HospitalItemUiState>() {
        override fun areItemsTheSame(
            oldItem: HospitalItemUiState,
            newItem: HospitalItemUiState
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HospitalItemUiState,
            newItem: HospitalItemUiState
        ): Boolean {
            return oldItem == newItem
        }

    }
}