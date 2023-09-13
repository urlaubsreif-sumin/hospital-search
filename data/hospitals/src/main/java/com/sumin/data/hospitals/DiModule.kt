package com.sumin.data.hospitals

import android.content.Context
import com.sumin.data.hospitals.local.HospitalLocalDataSource
import com.sumin.data.hospitals.local.HospitalLocalDataSourceImpl
import com.sumin.data.hospitals.remote.HospitalRemoteDataSource
import com.sumin.data.hospitals.remote.HospitalRemoteDataSourceImpl
import com.sumin.list.hospital.HospitalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiModule {

    @Singleton
    @Provides
    fun provideHospitalRepository(
        @ApplicationContext applicationContext: Context,
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
    fun provideHospitalLocalDataSource(@ApplicationContext applicationContext: Context): HospitalLocalDataSource {
        return HospitalLocalDataSourceImpl(applicationContext, Dispatchers.IO)
    }

    @Singleton
    @Provides
    fun provideHospitalRemoteDataSource(): HospitalRemoteDataSource {
        return HospitalRemoteDataSourceImpl(Dispatchers.IO)
    }
}