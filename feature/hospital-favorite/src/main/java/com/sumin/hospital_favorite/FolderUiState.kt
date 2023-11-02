package com.sumin.hospital_favorite


data class FolderListUiState(
    val list: List<FolderUiState> = emptyList(),
    val isFetching: Boolean = false,
    val message: String? = null
)

data class FolderUiState(
    val id: Int,
    val name: String,
    val color: Int,
    val isChecked: Boolean = false
)