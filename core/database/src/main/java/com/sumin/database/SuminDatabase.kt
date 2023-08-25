package com.sumin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HospitalEntity::class], version = 1)
abstract class SuminDatabase: RoomDatabase() {
    abstract fun hospitalDao(): HospitalDao

    companion object {
        private var INSTANCE: SuminDatabase? = null
        fun getInstance(context: Context): SuminDatabase {
            if(INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context, SuminDatabase::class.java, "sumin_database")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}