package com.sumin.data.hospitals

import android.util.Log
import com.sumin.list.hospital.HospitalModel
import com.sumin.list.hospital.HospitalRepository

class HospitalRepositoryImpl(
    private val hospitalLocalDataSource: HospitalLocalDataSource,
    private val hospitalRemoteDataSource: HospitalRemoteDataSource
) : HospitalRepository {

    override suspend fun getHospitalList(page: Int): List<HospitalModel> =
        hospitalRemoteDataSource.getHospitalList(page, NUM_OF_ROWS).map {
            it.toHospitalModel() }

    override suspend fun getHospitalListByHospitalName(
        name: String,
        page: Int
    ): List<HospitalModel> =
        hospitalRemoteDataSource.getHospitalListByHospitalName(name, page, NUM_OF_ROWS).map {
            it.toHospitalModel()
        }



    companion object {
        private const val NUM_OF_ROWS = 10
    }
}