package com.sumin.hospital_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumin.list.hospital.HospitalRepository
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
class HospitalDetailFragmentViewModel @Inject constructor(
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
                hospitalRepository.getHospitalDetailById(hospitalId!!)
                    .collectLatest { hospitalDetailModel ->
                        _uiState.update {
                            it.copy(
                                id = hospitalDetailModel.id,
                                codeName = hospitalDetailModel.codeName,
                                hospitalName = hospitalDetailModel.hospitalName,
                                sidoAddr = hospitalDetailModel.sidoAddr,
                                sgguAddr = hospitalDetailModel.sgguAddr,
                                telNo = hospitalDetailModel.telNo,
                                homepageUrl = hospitalDetailModel.hompageUrl,
                                estbDate = hospitalDetailModel.estbDate,
                                addr = hospitalDetailModel.addr,

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
}