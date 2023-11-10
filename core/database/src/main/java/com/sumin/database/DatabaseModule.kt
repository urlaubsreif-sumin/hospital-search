package com.sumin.database

import android.content.Context
import androidx.room.Room
import com.sumin.database.folder.FavoriteHospitalDao
import com.sumin.database.folder.FolderDao
import com.sumin.database.hospital.HospitalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): SuminDatabase {
        return Room.databaseBuilder(context, SuminDatabase::class.java, "sumin_database")
            .build()
    }

    @Singleton
    @Provides
    fun provideHospitalDao(database: SuminDatabase): HospitalDao {
        return database.hospitalDao()
    }

    @Singleton
    @Provides
    fun provideFolderDao(database: SuminDatabase): FolderDao {
        return database.folderDao()
    }

    @Singleton
    @Provides
    fun provideFavoriteHospitalDao(database: SuminDatabase): FavoriteHospitalDao {
        return database.favoriteHospitalDao()
    }
}