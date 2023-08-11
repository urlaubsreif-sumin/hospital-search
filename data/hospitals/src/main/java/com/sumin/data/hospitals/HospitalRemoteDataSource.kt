package com.sumin.data.hospitals

import android.util.Log
import com.sumin.data.hospitals.models.HospitalApiModel
import com.sumin.data.hospitals.models.HospitalListApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface HospitalRemoteDataSource {
    suspend fun getHospitalList(page: Int, numOfRows: Int): List<HospitalApiModel>
}

class HospitalRemoteDataSourceImpl(
    private val hospitalApi: HospitalApi,
    private val ioDispatcher: CoroutineDispatcher
) : HospitalRemoteDataSource {

    override suspend fun getHospitalList(page: Int, numOfRows: Int): List<HospitalApiModel> =
        withContext(ioDispatcher) {
            Log.i("[api test]", "remoteDataSource.getHospitalList -> page=$page")

            val result = hospitalApi.getHospitalList(pageNo = page, numOfRows = numOfRows)
                .body?.hospitalListApiModel

            result?.item ?: emptyList()
        }
}