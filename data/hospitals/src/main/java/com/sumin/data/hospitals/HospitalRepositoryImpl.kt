package com.sumin.data.hospitals

import android.util.Log
import androidx.paging.PagingSource
import com.sumin.data.hospitals.local.HospitalLocalDataSource
import com.sumin.data.hospitals.remote.HospitalRemoteDataSource
import com.sumin.list.hospital.HospitalDetailModel
import com.sumin.list.hospital.HospitalModel
import com.sumin.list.hospital.HospitalQuery
import com.sumin.list.hospital.HospitalRepository
import kotlinx.coroutines.flow.Flow

class HospitalRepositoryImpl(
    private val hospitalLocalDataSource: HospitalLocalDataSource,
    private val hospitalRemoteDataSource: HospitalRemoteDataSource
) : HospitalRepository {

    override fun getHospitalListByQuery(query: HospitalQuery): PagingSource<Int, HospitalModel> {
        return hospitalRemoteDataSource.getHospitalPagingByQuery(query) { hospitalApiModels ->
            val hospitalDetailModels = hospitalApiModels.map { it.toHospitalDetailModel() }
            addHospitalDetails(hospitalDetailModels)
        }
    }

    override suspend fun getHospitalDetailById(hospitalId: String): Flow<HospitalDetailModel> {
        return hospitalLocalDataSource.getHospitalDetailById(hospitalId)
    }

    override suspend fun addHospitalDetails(hospitalDetails: List<HospitalDetailModel>) {
        hospitalLocalDataSource.addHospitalDetails(hospitalDetails)
    }
}