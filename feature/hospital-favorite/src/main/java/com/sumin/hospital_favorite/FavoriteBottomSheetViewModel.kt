package com.sumin.hospital_favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteBottomSheetViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(FolderListUiState())
    val uiState: StateFlow<FolderListUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    private var isAllNotChecked = true

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
                val list = listOf(
                    FolderListItemUiState.FolderAdderUiState(),
                    FolderListItemUiState.FolderUiState(1, 1, "기본0", 0, true),
                    FolderListItemUiState.FolderUiState(2, 2, "기본1", 0),
                    FolderListItemUiState.FolderUiState(3, 3, "기본2", 0),
                    FolderListItemUiState.FolderUiState(4, 4, "기본3", 0),
                    FolderListItemUiState.FolderUiState(5, 5, "기본4", 0),
                    FolderListItemUiState.FolderUiState(6, 6, "기본5", 0)
                )

                isAllNotChecked = getIsAllNotChecked(list)

                _uiState.update {
                    it.copy(
                        list = list,
                        buttonEnabled = false,
                        buttonText = "저장",
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

    fun addFolder(folderName: String) {
        viewModelScope.launch {
            // TODO 폴더 추가하고, 갱신
        }
    }

    fun selectFolder(pos: Int, isChecked: Boolean) {
        val newList = uiState.value.getNewCheckedList(pos, isChecked)
        val isNewAllNotChecked = getIsAllNotChecked(newList)

        _uiState.update {
            it.copy(
                list = newList,
                buttonEnabled = !(isAllNotChecked && isNewAllNotChecked),
                buttonText = if (!isAllNotChecked && isNewAllNotChecked) {
                    "저장 취소"
                } else {
                    "저장"
                }
            )
        }
    }

    fun submitFavoriteResult() {
        // TODO: 즐겨찾기 결과 저장
    }

    private fun getIsAllNotChecked(list: List<FolderListItemUiState>): Boolean {
        return list.firstOrNull { it is FolderListItemUiState.FolderUiState && it.checked }
            ?.run {
                false
            } ?: true
    }

}