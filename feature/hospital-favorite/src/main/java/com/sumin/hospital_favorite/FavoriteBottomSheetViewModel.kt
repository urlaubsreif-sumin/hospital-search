package com.sumin.hospital_favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumin.folder_list.FavoriteHospitalModel
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

private val TAG = FavoriteBottomSheetViewModel::class.simpleName

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
            Log.i(TAG, "load folders!")
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
            Log.i(TAG, "error while load folders: ${e.message}")
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
            Log.i(TAG, "addFolder ---> name: $name")
            folderRepository.insertFolder(
                name, 0
            )
        }
    }

    fun selectFolder(id: Long, isChecked: Boolean) {
        Log.i(TAG, "selectFolder ---> id: $id / isChecked: $isChecked")
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

    suspend fun submitFavoriteResult(hospitalId: String) {
        val folderList = folderListUiState.value.list
        Log.i(TAG, "folder size = ${folderList.size}")

        folderList.forEachIndexed { idx, folder ->
            Log.i(TAG, "folder size = ${folderList.size}")
            Log.i(TAG, "folder ? ${folder} / $idx")
            if(folder is FolderListItemUiState.ItemFolderUiState) {
                val favoriteHospitalModel = FavoriteHospitalModel(
                    folderId = folder.id,
                    hospitalId = hospitalId
                )

                when(folder.checked) {
                    true -> {
                        Log.i(TAG, "INSERT FavoriteHospital: ${favoriteHospitalModel.hospitalId} / folder: ${folder.id}")
                        folderRepository.insertFavoriteHospital(favoriteHospitalModel)
                    }
                    false -> {
                        Log.i(TAG, "DELETE FavoriteHospital: ${favoriteHospitalModel.hospitalId} / folder: ${folder.id}")
                        folderRepository.deleteFavoriteHospital(favoriteHospitalModel)
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