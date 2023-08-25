package com.sumin.data.hospitals.local

import android.content.Context
import com.sumin.data.hospitals.toHospitalDetailModel
import com.sumin.data.hospitals.toHospitalEntity
import com.sumin.database.SuminDatabase
import com.sumin.list.hospital.HospitalDetailModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface HospitalLocalDataSource {
    suspend fun getHospitalDetailById(ykiho: String): Flow<HospitalDetailModel>
    suspend fun addHospitalDetails(hospitalDetails: List<HospitalDetailModel>)
}

class HospitalLocalDataSourceImpl(
    applicationContext: Context,
    private val ioDispatcher: CoroutineDispatcher
) : HospitalLocalDataSource {

    private val database = SuminDatabase.getInstance(applicationContext)

    override suspend fun getHospitalDetailById(ykiho: String): Flow<HospitalDetailModel> {
        val hospitalDao = database.hospitalDao()
        return hospitalDao.getHospitalDetail(ykiho).map { it.toHospitalDetailModel() }
    }

    override suspend fun addHospitalDetails(hospitalDetails: List<HospitalDetailModel>) {
        withContext(ioDispatcher) {
            val hospitalDao = database.hospitalDao()
            val array = hospitalDetails.map { it.toHospitalEntity() }.toTypedArray()
            hospitalDao.insertAll(*array)
        }
    }
}