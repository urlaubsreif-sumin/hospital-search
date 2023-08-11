package com.sumin.feature.search

import com.sumin.data.hospitals.HospitalApi
import com.sumin.data.hospitals.HospitalLocalDataSourceImpl
import com.sumin.data.hospitals.HospitalRemoteDataSourceImpl
import com.sumin.data.hospitals.HospitalRepositoryImpl
import com.sumin.list.hospital.HospitalRepository
import kotlinx.coroutines.Dispatchers

fun provideSearchFragmentViewModel(): SearchFragmentViewModel.Factory {
    return SearchFragmentViewModel.Factory(provideHospitalRepository())
}

private fun provideHospitalRepository(): HospitalRepository {
    return HospitalRepositoryImpl(
        HospitalLocalDataSourceImpl(),
        HospitalRemoteDataSourceImpl(HospitalApi.create(), Dispatchers.IO)
    )
}

