package com.sumin.hospital_favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumin.folder_list.FavoriteHospitalModel
import com.sumin.folder_list.FolderModel
import com.sumin.folder_list.FolderRepository
import com.sumin.folder_list.GetFavoriteHospitalStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteBottomSheetViewModel @Inject constructor(
    private val folderRepository: FolderRepository,
    private val getFavoriteHospitalStateUseCase: GetFavoriteHospitalStateUseCase
) : ViewModel() {

    /*리스트 목록 BottomSheetDialog*/
    private val _folderListUiState = MutableStateFlow(FolderListUiState())
    val folderListUiState: StateFlow<FolderListUiState> = _folderListUiState.asStateFlow()

    /*새 리스트 추가 Dialog*/
    private val _folderAdderDialogUiState = MutableStateFlow(FolderAdderDialogUiState())
    val folderAdderDialogUiState: StateFlow<FolderAdderDialogUiState> =
        _folderAdderDialogUiState.asStateFlow()

    private var fetchJob: Job? = null

    private var isAllNotChecked = true

    fun loadFavoriteFolderList(hospitalId: String) {
        try {
            _folderListUiState.update {
                it.copy(
                    isFetching = true,
                    message = null
                )
            }

            fetchJob?.cancel()
            fetchJob = viewModelScope.launch {
                getFavoriteHospitalStateUseCase(hospitalId).collectLatest {
                    val new_list = mutableListOf<FolderListItemUiState>()
                    new_list.add(0, FolderListItemUiState.ItemFolderAdderUiState())
                    val list = it.map { folderModel ->
                        FolderListItemUiState.ItemFolderUiState(
                            id = folderModel.id,
                            name = folderModel.name,
                            color = folderModel.color,
                            checked = folderModel.isChecked
                        )
                    }
                    new_list.addAll(list)

                    isAllNotChecked = getIsAllNotChecked(list)

                    _folderListUiState.update {
                        it.copy(
                            list = new_list,
                            buttonEnabled = false,
                            buttonText = "저장",
                            isFetching = false
                        )
                    }
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
            folderRepository.insertFolder(
                name, 0
            )
        }
    }

    fun selectFolder(id: Long, isChecked: Boolean) {
        _folderListUiState.update {
            val newList = it.getNewCheckedList(id, isChecked)
            val isNewAllNotChecked = getIsAllNotChecked(newList)

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

    fun submitFavoriteResult(hospitalId: String) {
        viewModelScope.launch {
            _folderListUiState.value.list.forEach { folder ->
                if(folder is FolderListItemUiState.ItemFolderUiState) {
                    val favoriteHospitalModel = FavoriteHospitalModel(
                        folderId = folder.id,
                        hospitalId = hospitalId
                    )

                    when(folder.checked) {
                        true -> {
                            folderRepository.insertFavoriteHospital(favoriteHospitalModel)
                        }
                        false -> {
                            folderRepository.deleteFavoriteHospital(favoriteHospitalModel)
                        }
                    }
                }
            }
        }
    }

    private fun getIsAllNotChecked(list: List<FolderListItemUiState>): Boolean {
        return list.firstOrNull { it is FolderListItemUiState.ItemFolderUiState && it.checked }
            ?.run {
                false
            } ?: true
    }
}