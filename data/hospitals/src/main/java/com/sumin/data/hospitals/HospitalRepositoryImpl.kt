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
import javax.inject.Inject

class HospitalRepositoryImpl @Inject constructor(
    private val hospitalLocalDataSource: HospitalLocalDataSource,
    private val hospitalRemoteDataSource: HospitalRemoteDataSource
) : HospitalRepository {

    override fun getHospitalListByQuery(query: HospitalQuery): PagingSource<Int, HospitalModel> {
        Log.i("[search test]", "getHospitalListByQuery($query)")
        return hospitalRemoteDataSource.getHospitalPagingByQuery(query) { hospitalApiModels ->
            Log.i("[search test]", "hospitalApiModels: ${hospitalApiModels.size}")
            val hospitalDetailModels = hospitalApiModels.map {
                it.toHospitalDetailModel()
            }
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