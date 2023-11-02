package com.sumin.hospital_favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteBottomSheetViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(FolderListUiState())
    val uiState: StateFlow<FolderListUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun loadFavoriteFolderList() {
        try {
            _uiState.update {
                it.copy(
                    isFetching = true,
                    message = null
                )
            }

            fetchJob?.cancel()
            fetchJob = viewModelScope.launch {
                // TODO: 즐겨찾기 목록 정보 + 현재 병원이 이미 추가되어 있는 리스트
                _uiState.update {
                    it.copy(
                        list = listOf(FolderUiState(1, "기본", 0)),
                        isFetching = false
                    )
                }
            }

        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    list = emptyList(),
                    isFetching = false,
                    message = "문제가 발생했습니다."
                )
            }
        }
    }

}