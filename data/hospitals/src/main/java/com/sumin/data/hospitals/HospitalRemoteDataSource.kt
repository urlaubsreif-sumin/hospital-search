package com.sumin.data.hospitals

import android.util.Log
import com.sumin.data.hospitals.models.HospitalApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface HospitalRemoteDataSource {
    suspend fun getHospitalList(page: Int, numOfRows: Int): List<HospitalApiModel>
    suspend fun getHospitalListByHospitalName(
        name: String,
        page: Int,
        numOfRows: Int
    ): List<HospitalApiModel>
}

class HospitalRemoteDataSourceImpl(
    private val hospitalApi: HospitalApi,
    private val ioDispatcher: CoroutineDispatcher
) : HospitalRemoteDataSource {

    override suspend fun getHospitalList(page: Int, numOfRows: Int): List<HospitalApiModel> =
        withContext(ioDispatcher) {
            val result = hospitalApi.getHospitalList(pageNo = page, numOfRows = numOfRows)
                .body?.hospitalListApiModel

            result?.item ?: emptyList()
        }

    override suspend fun getHospitalListByHospitalName(
        name: String,
        page: Int,
        numOfRows: Int
    ): List<HospitalApiModel> =
        withContext(ioDispatcher) {
            val result = hospitalApi.getHospitalList(hospitalName = name, pageNo = page, numOfRows = numOfRows)
                .body?.hospitalListApiModel

            result?.item ?: emptyList()
        }

}