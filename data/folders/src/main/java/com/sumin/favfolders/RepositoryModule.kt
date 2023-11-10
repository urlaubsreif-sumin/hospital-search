package com.sumin.favfolders

import com.sumin.database.folder.FavoriteHospitalDao
import com.sumin.database.folder.FolderDao
import com.sumin.favfolders.local.FolderLocalDataSource
import com.sumin.favfolders.local.FolderLocalDataSourceImpl
import com.sumin.folder_list.FolderRepository
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
    fun provideFolderRepositoryImpl(
        localDataSource: FolderLocalDataSource
    ): FolderRepository {
        return FolderRepositoryImpl(
            localDataSource
        )
    }

    @Singleton
    @Provides
    fun provideFolderLocalDataSource(
        folderDao: FolderDao,
        favoriteHospitalDao: FavoriteHospitalDao
    ): FolderLocalDataSource {
        return FolderLocalDataSourceImpl(Dispatchers.IO, folderDao, favoriteHospitalDao)
    }
}