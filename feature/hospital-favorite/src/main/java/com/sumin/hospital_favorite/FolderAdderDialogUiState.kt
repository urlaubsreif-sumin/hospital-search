package com.sumin.hospital_favorite

data class FolderAdderDialogUiState(
    val folderName: String = "",
    private val message: String = ""
) {
    fun getMessage() = if(folderName.isEmpty()) {
        "리스트 명을 입력해주세요."
    } else if(folderName.length > 20) {
        "리스트 명이 20자를 초과할 수 없습니다."
    } else {
        ""
    }
}