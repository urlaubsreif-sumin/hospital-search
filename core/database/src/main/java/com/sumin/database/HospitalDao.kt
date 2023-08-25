package com.sumin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sumin.database.HospitalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HospitalDao {
    @Query("SELECT * FROM hospitals WHERE ykiho = :ykiho")
    fun getHospitalDetail(ykiho: String): Flow<HospitalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg hospitals: HospitalEntity)
}