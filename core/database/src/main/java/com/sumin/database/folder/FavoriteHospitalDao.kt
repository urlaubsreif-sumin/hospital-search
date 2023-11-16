package com.sumin.database.folder

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteHospitalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteHospital(favoriteHospitalEntity: FavoriteHospitalEntity)

    @Delete
    fun deleteFavoriteHospital(favoriteHospitalEntity: FavoriteHospitalEntity)

    @Query("SELECT * FROM favorite_hospital WHERE folderId = :folderId")
    fun getFavoriteHospitalsInFolder(folderId: Long): Flow<List<FavoriteHospitalEntity>>

    @Query("SELECT EXISTS(SELECT * FROM favorite_hospital WHERE hospitalId = :hospitalId)")
    fun isFavoriteHospital(hospitalId: String): Boolean

    @Query("SELECT * FROM favorite_hospital WHERE hospitalId = :hospitalId")
    fun getFavoriteHospitalsByHospitalId(hospitalId: String): Flow<List<FavoriteHospitalEntity>>

}