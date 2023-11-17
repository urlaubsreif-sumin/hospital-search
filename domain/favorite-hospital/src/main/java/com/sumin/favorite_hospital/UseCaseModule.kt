package com.sumin.favorite_hospital

import com.sumin.folder_list.FolderRepository
import com.sumin.list.hospital.HospitalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetHospitalListWithFavoriteState(
        hospitalRepository: HospitalRepository,
        folderRepository: FolderRepository
    ): GetHospitalListWithFavoriteState {
        return GetHospitalListWithFavoriteState(
            folderRepository,
            hospitalRepository,
            Dispatchers.Default
        )
    }

    @Singleton
    @Provides
    fun provideGetHospitalDetailWithFavoriteState(
        hospitalRepository: HospitalRepository,
        folderRepository: FolderRepository
    ): GetHospitalDetailWithFavoriteState {
        return GetHospitalDetailWithFavoriteState(
            folderRepository,
            hospitalRepository,
            Dispatchers.Default
        )
    }
}