package com.sumin.feature.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.sumin.list.hospital.Constants
import com.sumin.list.hospital.HospitalQuery
import com.sumin.list.hospital.HospitalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val hospitalRepository: HospitalRepository
) : ViewModel() {

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
                    Log.i("[search test]", "pagingData: ${it.hospitalName}")
                    HospitalItemUiState(
                        id = it.id,
                        codeName = it.codeName,
                        hospitalName = it.hospitalName ?: "정보 없음",
                        sidoAddr = it.sidoAddr ?: "정보 없음",
                        sgguAddr = it.sgguAddr ?: "정보 없음"
                    )
                }

            }.catch { e ->
                Log.i("[search test]", "exception: ${e.message}")
//                TODO()

            }
    }


    fun onHospitalNameQueryChanged(name: String) {
        queryFlow.value = queryFlow.value.copy(yadmNm = name)
    }
}