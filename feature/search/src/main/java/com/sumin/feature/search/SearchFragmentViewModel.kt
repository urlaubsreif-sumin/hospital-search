package com.sumin.feature.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.sumin.list.hospital.Constants
import com.sumin.list.hospital.HospitalQuery
import com.sumin.list.hospital.HospitalRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import java.io.IOException

class SearchFragmentViewModel(
    private val hospitalRepository: HospitalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HospitalListUiState())
    val uiState: StateFlow<HospitalListUiState> = _uiState.asStateFlow()

    private val queryFlow = MutableStateFlow(HospitalQuery())

    @OptIn(ExperimentalCoroutinesApi::class)
    val queryResult = queryFlow.flatMapLatest { query ->
        Pager(
            PagingConfig(pageSize = Constants.PAGE_SIZE)
        ) {
            hospitalRepository.getHospitalListByQuery(query)
        }
            .flow
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .map { pagingData ->
                pagingData.map {
                    Log.i("[search test]", "UI ITEM ---> ${it.hospitalName}")
                    HospitalItemUiState(
                        id = it.id,
                        code = it.code,
                        hospitalName = it.hospitalName ?: "정보 없음",
                        sidoAddr = it.sidoAddr ?: "정보 없음",
                        sgguAddr = it.sgguAddr ?: "정보 없음"
                    )
                }

            }.catch { e ->
                _uiState.update {
                    val messages = getMessageFromThrowable(e)
                    it.copy(
                        messages = messages,
                        isFetchingHospitals = false
                    )
                }
            }
    }


    fun onHospitalNameQueryChanged(name: String) {
        queryFlow.value = queryFlow.value.copy(yadmNm = name)
    }


    private fun getMessageFromThrowable(e: Throwable): List<Message> {
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