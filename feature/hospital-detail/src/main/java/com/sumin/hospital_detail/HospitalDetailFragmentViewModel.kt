package com.sumin.hospital_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sumin.list.hospital.HospitalRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HospitalDetailFragmentViewModel(
    private val hospitalRepository: HospitalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HospitalDetailUiState())
    val uiState: StateFlow<HospitalDetailUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun loadHospitalDetail(hospitalId: String?) {
        try {
            _uiState.update { uiState ->
                hospitalId!!.let {
                    uiState.copy(
                        isFetchingHospitalDetail = true
                    )
                }
            }

            fetchJob?.cancel()
            fetchJob = viewModelScope.launch {
                hospitalRepository.getHospitalDetailById(hospitalId!!).collectLatest {hospitalDetailModel ->
                    _uiState.update {
                        it.copy(
                            telNo = hospitalDetailModel.telNo ?: "정보없음",
                            homepageUrl = hospitalDetailModel.hompageUrl ?: "정보없음",
                            estbDate = hospitalDetailModel.estbDate ?: "정보없음",
                            addr = hospitalDetailModel.addr ?: "정보없음",

                            isFetchingHospitalDetail = false
                        )
                    }
                }
            }

        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    message = "문제가 발생했습니다.",
                    isFetchingHospitalDetail = false
                )
            }
        }
    }

    class Factory(
        private val hospitalRepository: HospitalRepository
    ) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HospitalDetailFragmentViewModel(hospitalRepository) as T
        }
    }
}