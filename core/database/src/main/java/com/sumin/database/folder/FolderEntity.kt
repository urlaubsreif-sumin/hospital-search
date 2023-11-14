package com.sumin.database.folder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folders")
data class FolderEntity(
    val name: String,
    val color: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}