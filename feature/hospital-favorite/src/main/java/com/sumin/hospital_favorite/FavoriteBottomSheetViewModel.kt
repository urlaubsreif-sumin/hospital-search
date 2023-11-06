package com.sumin.hospital_favorite

import androidx.databinding.Bindable
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
    /*리스트 목록 BottomSheetDialog*/
    private val _folderListUiState = MutableStateFlow(FolderListUiState())
    val folderListUiState: StateFlow<FolderListUiState> = _folderListUiState.asStateFlow()

    /*새 리스트 추가 Dialog*/
    private val _folderAdderDialogUiState = MutableStateFlow(FolderAdderDialogUiState())
    val folderAdderDialogUiState: StateFlow<FolderAdderDialogUiState> =
        _folderAdderDialogUiState.asStateFlow()

    private var fetchJob: Job? = null

    private var isAllNotChecked = true

    fun loadFavoriteFolderList() {
        try {
            _folderListUiState.update {
                it.copy(
                    isFetching = true,
                    message = null
                )
            }

            fetchJob?.cancel()
            fetchJob = viewModelScope.launch {
                // TODO: 즐겨찾기 목록 정보 + 현재 병원이 이미 추가되어 있는 리스트
                val list = listOf(
                    FolderListItemUiState.ItemFolderAdderUiState(),
                    FolderListItemUiState.ItemFolderUiState(1, 1, "기본0", 0, true),
                    FolderListItemUiState.ItemFolderUiState(2, 2, "기본1", 0),
                    FolderListItemUiState.ItemFolderUiState(3, 3, "기본2", 0),
                    FolderListItemUiState.ItemFolderUiState(4, 4, "기본3", 0),
                    FolderListItemUiState.ItemFolderUiState(5, 5, "기본4", 0),
                    FolderListItemUiState.ItemFolderUiState(6, 6, "기본5", 0)
                )

                isAllNotChecked = getIsAllNotChecked(list)

                _folderListUiState.update {
                    it.copy(
                        list = list,
                        buttonEnabled = false,
                        buttonText = "저장",
                        isFetching = false
                    )
                }
            }

        } catch (e: Exception) {
            _folderListUiState.update {
                it.copy(
                    list = emptyList(),
                    isFetching = false,
                    message = "문제가 발생했습니다."
                )
            }
        }
    }

    fun addFolder(name: String) {
        viewModelScope.launch {
            // TODO 폴더 추가하고, 갱신
        }
    }

    fun selectFolder(pos: Int, isChecked: Boolean) {
        val newList = folderListUiState.value.getNewCheckedList(pos, isChecked)
        val isNewAllNotChecked = getIsAllNotChecked(newList)

        _folderListUiState.update {
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

    fun onChangeFolderName(value: String) {
        _folderAdderDialogUiState.update {
            it.copy(
                folderName = value
            )
        }
    }

    fun submitFavoriteResult() {
        // TODO: 즐겨찾기 결과 저장
    }

    private fun getIsAllNotChecked(list: List<FolderListItemUiState>): Boolean {
        return list.firstOrNull { it is FolderListItemUiState.ItemFolderUiState && it.checked }
            ?.run {
                false
            } ?: true
    }

}