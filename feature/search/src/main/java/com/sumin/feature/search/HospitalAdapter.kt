package com.sumin.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumin.feature.search.databinding.ItemHospitalBinding

class HospitalAdapter: RecyclerView.Adapter<HospitalAdapter.ViewHolder>() {
    private var hospitalItems: List<HospitalItemUiState> = emptyList()

    fun setData(items: List<HospitalItemUiState>) {
        hospitalItems = items
        notifyItemRangeInserted(0, items.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = hospitalItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hospitalItems[position])
    }


    class ViewHolder(private val binding: ItemHospitalBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(hospitalItem: HospitalItemUiState) {
            binding.hospitalItem = hospitalItem
        }
    }
}