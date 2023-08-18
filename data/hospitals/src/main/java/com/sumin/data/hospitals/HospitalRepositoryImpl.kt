package com.sumin.data.hospitals

import android.util.Log
import androidx.paging.PagingSource
import com.sumin.list.hospital.Constants
import com.sumin.list.hospital.HospitalModel
import com.sumin.list.hospital.HospitalQuery
import com.sumin.list.hospital.HospitalRepository

class HospitalRepositoryImpl(
    private val hospitalLocalDataSource: HospitalLocalDataSource,
    private val hospitalRemoteDataSource: HospitalRemoteDataSource
) : HospitalRepository {

    override suspend fun getHospitalList(page: Int): List<HospitalModel> =
        hospitalRemoteDataSource.getHospitalList(page, Constants.PAGE_SIZE).map {
            it.toHospitalModel() }

    override suspend fun getHospitalListByHospitalName(
        name: String,
        page: Int
    ): List<HospitalModel> =
        hospitalRemoteDataSource.getHospitalListByHospitalName(name, page, Constants.PAGE_SIZE).map {
            it.toHospitalModel()
        }

    override fun getHospitalListByQuery(query: HospitalQuery): PagingSource<Int, HospitalModel> {
        return HospitalPagingSource(
            HospitalApi.create(),
            query
        )
    }
}