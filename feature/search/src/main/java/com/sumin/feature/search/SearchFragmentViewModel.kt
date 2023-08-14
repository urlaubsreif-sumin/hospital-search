package com.sumin.feature.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sumin.list.hospital.HospitalRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class SearchFragmentViewModel(
    private val hospitalRepository: HospitalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HospitalListUiState())
    val uiState: StateFlow<HospitalListUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun searchHospitals(query: String, page: Int) {
        _uiState.update {
            it.copy(isFetchingHospitals = true)
        }

        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                val hospitalItemUiStates = hospitalRepository.getHospitalListByHospitalName(query, page).map {
                    HospitalItemUiState(
                        code = it.code,
                        hospitalName = it.hospitalName ?: "정보 없음",
                        sidoAddr = it.sidoAddr ?: "정보 없음",
                        sgguAddr = it.sgguAddr ?: "정보 없음"
                    )
                }

                _uiState.update {
                    it.copy(
                        items = hospitalItemUiStates,
                        isFetchingHospitals = false,
                        messages = listOf()
                    )
                }

            } catch (e: Exception) {
                _uiState.update {
                    val messages = getMessageFromThrowable(e)
                    it.copy(
                        messages = messages,
                        isFetchingHospitals = false)
                }
            }
        }
    }

    private fun getMessageFromThrowable(e: Exception): List<Message> {
        val message = when (e) {
            is IOException -> Message(0, "해당하는 정보가 없습니다.")
            else -> Message(1, "알 수 없는 오류가 발생했습니다.")
        }
        return listOf(message)
    }

    class Factory(
        private val hospitalRepository: HospitalRepository
    ) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SearchFragmentViewModel(hospitalRepository) as T
        }
    }
}