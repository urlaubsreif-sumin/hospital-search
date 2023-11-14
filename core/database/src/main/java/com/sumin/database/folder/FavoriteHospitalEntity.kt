package com.sumin.database.folder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_hospital", primaryKeys = ["hospitalId", "folderId"])
data class FavoriteHospitalEntity(
    val hospitalId: String,
    val folderId: Long
)