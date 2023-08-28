package com.sumin.hospital_detail

data class HospitalDetailUiState(
    val id: String = "정보없음",
    val codeName: String = "정보없음",
    val hospitalName: String = "정보없음",
    val sidoAddr: String = "정보없음",
    val sgguAddr: String = "정보없음",

    val telNo: String = "정보없음",
    val homepageUrl: String = "정보없음",
    val estbDate: String = "정보없음",
    val addr: String = "정보없음",

    val message: String? = null,
    val isFetchingHospitalDetail: Boolean = false
)