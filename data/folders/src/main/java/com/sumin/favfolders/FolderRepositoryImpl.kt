package com.sumin.favfolders

import com.sumin.favfolders.local.FolderLocalDataSource
import com.sumin.folder_list.FavoriteHospitalModel
import com.sumin.folder_list.FolderModel
import com.sumin.folder_list.FolderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FolderRepositoryImpl @Inject constructor(
    private val folderLocalDataSource: FolderLocalDataSource
) : FolderRepository {

    override suspend fun getFolderList(): Flow<List<FolderModel>> {
        return folderLocalDataSource.getAllFolders()
    }

    override suspend fun insertFolder(folder: FolderModel) {
        folderLocalDataSource.insertFolder(folder)
    }

    override suspend fun updateFolder(folder: FolderModel) {
        folderLocalDataSource.updateFolder(folder)
    }

    override suspend fun deleteFolders(vararg folder: FolderModel) {
        folderLocalDataSource.deleteFolder(*folder)
    }

    override suspend fun insertFavoriteHospital(folder: FavoriteHospitalModel) {
        folderLocalDataSource.insertFavoriteHospital(folder)
    }

    override suspend fun deleteFavoriteHospital(folder: FavoriteHospitalModel) {
        folderLocalDataSource.deleteFavoriteHospital(folder)
    }

    override suspend fun getFavoriteHospitalsByHospitalId(hospitalId: String): Flow<List<FavoriteHospitalModel>> {
        return folderLocalDataSource.getFavoriteHospitalsByHospitalId(hospitalId)
    }
}