package com.sumin.data.hospitals.local

import com.sumin.data.hospitals.toHospitalDetailModel
import com.sumin.data.hospitals.toHospitalEntity
import com.sumin.database.HospitalDao
import com.sumin.list.hospital.HospitalDetailModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface HospitalLocalDataSource {
    suspend fun getHospitalDetailById(ykiho: String): Flow<HospitalDetailModel>
    suspend fun addHospitalDetails(hospitalDetails: List<HospitalDetailModel>)
}

class HospitalLocalDataSourceImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val hospitalDao: HospitalDao
) : HospitalLocalDataSource {

    override suspend fun getHospitalDetailById(ykiho: String): Flow<HospitalDetailModel> {
        return hospitalDao.getHospitalDetail(ykiho).map { it.toHospitalDetailModel() }
    }

    override suspend fun addHospitalDetails(hospitalDetails: List<HospitalDetailModel>) {
        withContext(ioDispatcher) {
            val array = hospitalDetails.map { it.toHospitalEntity() }.toTypedArray()
            hospitalDao.insertAll(*array)
        }
    }
}