package com.sumin.database

import android.content.Context
import androidx.room.Room
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
}