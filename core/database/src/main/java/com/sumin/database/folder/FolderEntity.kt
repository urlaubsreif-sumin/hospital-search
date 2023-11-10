package com.sumin.database.folder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folders")
data class FolderEntity(
    @PrimaryKey val id: Long?,
    val name: String,
    val color: Int
)