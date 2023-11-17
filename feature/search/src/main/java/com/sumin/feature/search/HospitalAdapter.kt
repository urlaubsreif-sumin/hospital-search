package com.sumin.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sumin.feature.search.databinding.ItemHospitalGridBinding

class HospitalAdapter(
    private val onItemClick: (String) -> Unit,
    private val onFavoriteClick: (String) -> Unit
) : PagingDataAdapter<HospitalItemUiState, RecyclerView.ViewHolder>(UiModelComparator) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is HospitalViewHolder) {
            holder.bind(item as HospitalItemUiState)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val item = getItem(position)
        if(holder is HospitalViewHolder && item != null) {
            if(payloads.isEmpty()) {
                holder.bind(item)

            } else {
                payloads.forEach { payload ->
                    when (payload) {
                        PAYLOAD_FAVORITE -> {
                            holder.setFavorite(true)
                        }
                        PAYLOAD_UNFAVORITE -> {
                            holder.setFavorite(false)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemHospitalGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HospitalViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return when (peek(position)) {
            is HospitalItemUiState -> R.layout.item_hospital_grid
            else -> throw IllegalStateException("Unknown View")
        }
    }

    fun setFavorite(hospitalId: String, isFavorite: Boolean) {
        val position = this.snapshot().items.indexOfFirst { it.id == hospitalId }
        when(isFavorite) {
            true -> notifyItemChanged(position, PAYLOAD_FAVORITE)
            false -> notifyItemChanged(position, PAYLOAD_UNFAVORITE)
        }
    }

    inner class HospitalViewHolder(private val binding: ItemHospitalGridBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hospitalItem: HospitalItemUiState) {
            binding.hospitalItem = hospitalItem
            binding.btnFavorite.setOnClickListener {
                onFavoriteClick(hospitalItem.id)
            }
            binding.root.setOnClickListener { onItemClick(hospitalItem.id) }
        }

        fun setFavorite(isFavorite: Boolean) {
            val hospitalItem = binding.hospitalItem?.copy(
                isFavorite = isFavorite
            )
            binding.hospitalItem = hospitalItem
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

    companion object {
        private const val PAYLOAD_FAVORITE = "com.sumin.feature.search.PAYLOAD_FAVORITE"
        private const val PAYLOAD_UNFAVORITE = "com.sumin.feature.search.PAYLOAD_UNFAVORITE"
    }
}