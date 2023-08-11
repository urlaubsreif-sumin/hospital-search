package com.sumin.feature.search

data class HospitalListUiState(
    val items: List<HospitalItemUiState> = listOf(),
    val messages: List<Message> = listOf(),
    val isFetchingHospitals: Boolean = false
)

data class Message(val id: Long, val message: String)

data class HospitalItemUiState(
    val hospitalName: String,
    val telephoneNumber: String,
    val homepageUrl: String,
    val address: String
)