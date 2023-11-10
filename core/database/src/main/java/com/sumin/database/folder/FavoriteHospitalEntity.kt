package com.sumin.database.folder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_hospital")
data class FavoriteHospitalEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val hospitalId: String,
    val folderId: Long
)