package com.sumin.data.hospitals

import com.sumin.data.hospitals.local.HospitalLocalDataSource
import com.sumin.data.hospitals.local.HospitalLocalDataSourceImpl
import com.sumin.data.hospitals.remote.HospitalRemoteDataSource
import com.sumin.data.hospitals.remote.HospitalRemoteDataSourceImpl
import com.sumin.database.hospital.HospitalDao
import com.sumin.list.hospital.HospitalRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryImplModule {

    @Singleton
    @Provides
    fun provideHospitalRepositoryImpl(
        localDataSource: HospitalLocalDataSource,
        remoteDataSource: HospitalRemoteDataSource
    ): HospitalRepository {

        return HospitalRepositoryImpl(
            localDataSource,
            remoteDataSource
        )
    }

    @Singleton
    @Provides
    fun provideHospitalLocalDataSource(hospitalDao: HospitalDao): HospitalLocalDataSource {
        return HospitalLocalDataSourceImpl(Dispatchers.IO, hospitalDao)
    }

    @Singleton
    @Provides
    fun provideHospitalRemoteDataSource(): HospitalRemoteDataSource {
        return HospitalRemoteDataSourceImpl(Dispatchers.IO)
    }
}