package com.sumin.data.hospitals.remote

import android.util.Log
import androidx.paging.PagingSource
import com.sumin.data.hospitals.HospitalPagingSource
import com.sumin.list.hospital.HospitalModel
import com.sumin.list.hospital.HospitalQuery
import kotlinx.coroutines.CoroutineDispatcher

interface HospitalRemoteDataSource {
    fun getHospitalPagingByQuery(
        query: HospitalQuery,
        getHospitalModels: suspend (List<HospitalApiModel>) -> Unit
    ): PagingSource<Int, HospitalModel>
}

class HospitalRemoteDataSourceImpl(
    private val ioDispatcher: CoroutineDispatcher
) : HospitalRemoteDataSource {

    private val hospitalApi = HospitalApi.create()

    override fun getHospitalPagingByQuery(
        query: HospitalQuery,
        getHospitalModels: suspend (List<HospitalApiModel>) -> Unit
    ): PagingSource<Int, HospitalModel> {
        Log.i("[search test]", "RemoteDataSource.getHospitalPagingByQuery")
        return HospitalPagingSource(
            hospitalApi,
            query,
            getHospitalModels
        )
    }
}