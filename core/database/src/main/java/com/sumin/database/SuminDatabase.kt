package com.sumin.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.sumin.database.folder.FavoriteHospitalDao
import com.sumin.database.folder.FavoriteHospitalEntity
import com.sumin.database.folder.FolderDao
import com.sumin.database.folder.FolderEntity
import com.sumin.database.hospital.HospitalDao
import com.sumin.database.hospital.HospitalEntity

@Database(
    entities = [HospitalEntity::class, FolderEntity::class, FavoriteHospitalEntity::class],
    version = 1
)
abstract class SuminDatabase : RoomDatabase() {
    abstract fun hospitalDao(): HospitalDao
    abstract fun favoriteHospitalDao(): FavoriteHospitalDao
    abstract fun folderDao(): FolderDao
}