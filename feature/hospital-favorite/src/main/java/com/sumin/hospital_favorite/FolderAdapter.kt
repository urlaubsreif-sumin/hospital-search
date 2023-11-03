package com.sumin.hospital_favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sumin.hospital_favorite.databinding.HolderFavoriteFolderAddBinding
import com.sumin.hospital_favorite.databinding.HolderFavoriteFolderBinding

class FolderAdapter(
    private val onSelectAddFolder: () -> Unit,
    private val onSelectFolder: (position: Int, isChecked: Boolean) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var folderList: List<FolderListItemUiState> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VIEW_TYPE_FOLDER_ADDER -> {
                val binding = HolderFavoriteFolderAddBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return FolderAdderViewHolder(binding)
            }

            else -> {
                val binding = HolderFavoriteFolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return FolderViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (folderList[position]) {
            is FolderListItemUiState.FolderUiState -> {
                (holder as FolderViewHolder).bind(folderList[position] as FolderListItemUiState.FolderUiState)
            }

            is FolderListItemUiState.FolderAdderUiState -> {
                (holder as FolderAdderViewHolder).bind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (folderList[position] is FolderListItemUiState.FolderAdderUiState) {
            return VIEW_TYPE_FOLDER_ADDER
        } else {
            return VIEW_TYPE_FOLDER
        }
    }

    override fun getItemCount(): Int = folderList.size


    fun submitData(list: List<FolderListItemUiState>) {
        val diffCallback = DiffUtilCallback(this.folderList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        folderList = list
        diffResult.dispatchUpdatesTo(this@FolderAdapter)
    }

    /* 폴더 */
    inner class FolderViewHolder(private val binding: HolderFavoriteFolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(folderItem: FolderListItemUiState.FolderUiState) {
            binding.apply {
                root.setOnClickListener {
                    onSelectFolder(folderItem.position, !folderItem.checked)
                }

                checkFolder.isChecked = folderItem.checked
                tvFolderName.text = folderItem.name
            }
        }
    }

    /* 폴더 추가하기 */
    inner class FolderAdderViewHolder(private val binding: HolderFavoriteFolderAddBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.root.setOnClickListener { onSelectAddFolder() }
        }
    }

    inner class DiffUtilCallback(
        private val oldList: List<FolderListItemUiState>,
        private val newList: List<FolderListItemUiState>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            return if(oldItem is FolderListItemUiState.FolderAdderUiState && newItem is FolderListItemUiState.FolderAdderUiState) {
                true
            } else if(oldItem is FolderListItemUiState.FolderUiState && newItem is FolderListItemUiState.FolderUiState) {
                oldItem.id == newItem.id
            } else {
                false
            }
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }


    companion object {
        private const val VIEW_TYPE_FOLDER_ADDER = 1
        private const val VIEW_TYPE_FOLDER = 2
    }
}