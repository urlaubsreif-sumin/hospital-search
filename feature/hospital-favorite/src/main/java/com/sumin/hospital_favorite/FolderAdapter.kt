package com.sumin.hospital_favorite

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumin.hospital_favorite.databinding.HolderFavoriteFolderBinding

class FolderAdapter: RecyclerView.Adapter<FolderAdapter.FolderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class FolderViewHolder(val binding: HolderFavoriteFolderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(folderItem: FolderUiState) {

        }
    }
}