package com.sumin.hospital_favorite


data class FolderListUiState(
    val list: List<FolderListItemUiState> = emptyList(),
    val isFetching: Boolean = false,
    val buttonEnabled: Boolean = false,
    val buttonText: String = "저장",
    val message: String? = null
) {

    fun getNewCheckedList(id: Long, isChecked: Boolean): List<FolderListItemUiState> {
        val newList = list.toMutableList()
        newList.replaceAll { item ->
            if (item is FolderListItemUiState.ItemFolderUiState && item.id == id) {
                item.getCheckedState(isChecked)

            } else {
                item
            }
        }

        return newList
    }
}

sealed class FolderListItemUiState {
    data class ItemFolderUiState(
        val id: Long,
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