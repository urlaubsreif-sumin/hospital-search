package com.sumin.list.hospital

import java.io.Serializable

data class HospitalModel(
    val id: String,
    val codeName: String,
    val hospitalName: String?,
    val sidoAddr: String?,
    val sgguAddr: String?
): Serializable