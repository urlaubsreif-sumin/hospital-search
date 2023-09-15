package com.sumin.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HospitalEntity::class], version = 1)
abstract class SuminDatabase : RoomDatabase() {
    abstract fun hospitalDao(): HospitalDao
}