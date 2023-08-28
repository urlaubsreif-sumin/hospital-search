package com.sumin.list.hospital

data class HospitalDetailModel(
    val id: String,
    val codeName: String,
    val hospitalName: String?,
    val sidoAddr: String?,
    val sgguAddr: String?,

    val telNo: String?,
    val hompageUrl: String?,
    val estbDate: String?,
    val addr: String?
)