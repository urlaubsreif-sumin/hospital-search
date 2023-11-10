package com.sumin.database.folder

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFolder(folder: FolderEntity)

    @Delete
    fun deleteFolder(vararg folder: FolderEntity)

    @Update
    fun updateFolder(folder: FolderEntity)

    @Query("SELECT * FROM folders")
    fun getAllFolders(): Flow<List<FolderEntity>>
}