package com.sumin.folder_list

data class FolderModel(
    val id: Long,
    val name: String,
    val color: Int,
    val isChecked: Boolean = false
)