package com.sumin.folder_list

import kotlinx.coroutines.flow.Flow

interface FolderRepository {
    suspend fun getFolderList(): Flow<List<FolderModel>>

    suspend fun insertFolder(folderName: String, color: Int)

    suspend fun updateFolder(folder: FolderModel)

    suspend fun deleteFolders(vararg folder: FolderModel)


    suspend fun insertFavoriteHospital(folder: FavoriteHospitalModel)

    suspend fun deleteFavoriteHospital(folder: FavoriteHospitalModel)

    suspend fun getFavoriteHospitalsByHospitalId(hospitalId: String): Flow<List<FavoriteHospitalModel>>
}