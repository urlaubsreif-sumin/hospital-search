package com.sumin.hospital_detail

data class HospitalDetailUiState(
    val id: String = "정보없음",
    val codeName: String = "정보없음",
    val hospitalName: String? = null,
    val sidoAddr: String? = null,
    val sgguAddr: String? = null,

    val telNo: String? = null,
    val homepageUrl: String? = null,
    val estbDate: String? = null,
    val addr: String? = null,
    val isFavorite: Boolean = false,

    val message: String? = null,
    val isFetchingHospitalDetail: Boolean = false
)