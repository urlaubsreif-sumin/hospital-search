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
            (newList[pos] as FolderListItemUiState.FolderUiState).getCheckedState(isChecked)

        return newList
    }
}

sealed class FolderListItemUiState {
    data class FolderUiState(
        val id: Int,
        val position: Int,
        val name: String,
        val color: Int,
        val checked: Boolean = false
    ) : FolderListItemUiState() {

        fun getCheckedState(isChecked: Boolean): FolderUiState {
            return this.copy(
                checked = isChecked
            )
        }
    }

    data class FolderAdderUiState(
        val isAvailable: Boolean = false
    ) : FolderListItemUiState()
}