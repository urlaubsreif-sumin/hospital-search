package com.sumin.list.hospital

interface HospitalRepository {
    suspend fun getHospitalList(page: Int): List<HospitalModel>
}