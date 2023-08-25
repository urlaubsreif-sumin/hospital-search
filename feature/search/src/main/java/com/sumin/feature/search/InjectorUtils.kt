package com.sumin.feature.search

import android.content.Context
import com.sumin.data.hospitals.remote.HospitalApi
import com.sumin.data.hospitals.local.HospitalLocalDataSourceImpl
import com.sumin.data.hospitals.remote.HospitalRemoteDataSourceImpl
import com.sumin.data.hospitals.HospitalRepositoryImpl
import com.sumin.list.hospital.HospitalRepository
import kotlinx.coroutines.Dispatchers

fun provideSearchFragmentViewModel(context: Context): SearchFragmentViewModel.Factory {
    return SearchFragmentViewModel.Factory(provideHospitalRepository(context.applicationContext))
}

private fun provideHospitalRepository(applicationContext: Context): HospitalRepository {
    return HospitalRepositoryImpl(
        HospitalLocalDataSourceImpl(applicationContext, Dispatchers.IO),
        HospitalRemoteDataSourceImpl(Dispatchers.IO)
    )
}

