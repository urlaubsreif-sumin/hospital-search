package com.sumin.list.hospital

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow

interface HospitalRepository {
    fun getHospitalListByQuery(query: HospitalQuery): PagingSource<Int, HospitalModel>
    suspend fun getHospitalDetailById(hospitalId: String): Flow<HospitalDetailModel>
    suspend fun addHospitalDetails(hospitalDetails: List<HospitalDetailModel>)
}