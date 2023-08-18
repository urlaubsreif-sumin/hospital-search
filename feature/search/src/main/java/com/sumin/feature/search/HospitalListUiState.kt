package com.sumin.feature.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HospitalListUiState(
    val items: List<HospitalItemUiState> = listOf(),
    val messages: List<Message> = listOf(),
    val isFetchingHospitals: Boolean = false
)

data class Message(val id: Long, val message: String)

data class HospitalItemUiState(
    val id: String,
    val code: String,
    val hospitalName: String,
    val sidoAddr: String,
    val sgguAddr: String
)