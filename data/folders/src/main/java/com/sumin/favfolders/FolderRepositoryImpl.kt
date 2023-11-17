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

    override suspend fun insertFolder(folderName: String, color: Int) {
        folderLocalDataSource.insertFolder(folderName, color)
    }

    override suspend fun updateFolder(folder: FolderModel) {
        folderLocalDataSource.updateFolder(folder)
    }

    override suspend fun deleteFolders(vararg folder: FolderModel) {
        folder.forEach { folder ->
            val favoriteHospitals = getFavoriteHospitalsByFolderId(folder.id)
            deleteFavoriteHospital(*favoriteHospitals.toTypedArray())
        }
        folderLocalDataSource.deleteFolder(*folder)
    }

    override suspend fun insertFavoriteHospital(favoriteHospital: FavoriteHospitalModel) {
        folderLocalDataSource.insertFavoriteHospital(favoriteHospital)
    }

    override suspend fun deleteFavoriteHospital(vararg favoriteHospital: FavoriteHospitalModel) {
        folderLocalDataSource.deleteFavoriteHospital(*favoriteHospital)
    }

    override suspend fun isFavoriteHospital(hospitalId: String): Boolean {
        return folderLocalDataSource.isFavoriteHospital(hospitalId)
    }

    override suspend fun getFavoriteHospitalsByHospitalId(hospitalId: String): Flow<List<FavoriteHospitalModel>> {
        return folderLocalDataSource.getFavoriteHospitalsByHospitalId(hospitalId)
    }

    override suspend fun getFavoriteHospitalsByFolderId(folderId: Long): List<FavoriteHospitalModel> {
        return folderLocalDataSource.getFavoriteHospitalsByFolderId(folderId)
    }
}