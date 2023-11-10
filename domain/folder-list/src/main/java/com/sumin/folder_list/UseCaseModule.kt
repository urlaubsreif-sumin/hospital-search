package com.sumin.folder_list

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
    fun provideGetFavoriteHospitalStateUseCase(
        folderRepository: FolderRepository
    ): GetFavoriteHospitalStateUseCase {
        return GetFavoriteHospitalStateUseCase(folderRepository, Dispatchers.Default)
    }

}