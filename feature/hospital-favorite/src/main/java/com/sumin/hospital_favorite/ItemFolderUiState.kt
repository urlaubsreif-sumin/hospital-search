package com.sumin.hospital_favorite


data class FolderListUiState(
    val list: List<FolderListItemUiState> = emptyList(),
    val isFetching: Boolean = false,
    val buttonEnabled: Boolean = false,
    val buttonText: String = "저장",
    val message: String? = null
) {

    fun getNewCheckedList(pos: Int, isChecked: Boolean): List<FolderListItemUiState> {
        val newList = list.toMutableList()
        newList[pos] =
            (newList[pos] as FolderListItemUiState.ItemFolderUiState).getCheckedState(isChecked)

        return newList
    }
}

sealed class FolderListItemUiState {
    data class ItemFolderUiState(
        val id: Long? = 0L,
        val position: Int,
        val name: String,
        val color: Int,
        val checked: Boolean = false
    ) : FolderListItemUiState() {

        fun getCheckedState(isChecked: Boolean): ItemFolderUiState {
            return this.copy(
                checked = isChecked
            )
        }
    }

    data class ItemFolderAdderUiState(
        val isAvailable: Boolean = false
    ) : FolderListItemUiState()
}