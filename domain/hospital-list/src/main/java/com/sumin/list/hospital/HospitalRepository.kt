package com.sumin.list.hospital

import androidx.paging.PagingSource

interface HospitalRepository {
    suspend fun getHospitalList(page: Int): List<HospitalModel>
    suspend fun getHospitalListByHospitalName(name: String, page: Int): List<HospitalModel>
    fun getHospitalListByQuery(query: HospitalQuery): PagingSource<Int, HospitalModel>
}