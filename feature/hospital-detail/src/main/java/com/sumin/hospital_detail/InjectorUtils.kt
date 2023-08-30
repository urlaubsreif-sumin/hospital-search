package com.sumin.hospital_detail

import android.content.Context
import com.sumin.data.hospitals.HospitalRepositoryImpl
import com.sumin.data.hospitals.local.HospitalLocalDataSourceImpl
import com.sumin.data.hospitals.remote.HospitalRemoteDataSourceImpl
import com.sumin.list.hospital.HospitalRepository
import kotlinx.coroutines.Dispatchers

fun provideHospitalDetailFragmentViewModel(
    context: Context
): HospitalDetailFragmentViewModel.Factory {
    return HospitalDetailFragmentViewModel.Factory(
        provideHospitalRepository(context.applicationContext)
    )
}

private fun provideHospitalRepository(applicationContext: Context): HospitalRepository {
    return HospitalRepositoryImpl(
        HospitalLocalDataSourceImpl(applicationContext, Dispatchers.IO),
        HospitalRemoteDataSourceImpl(Dispatchers.IO)
    )
}