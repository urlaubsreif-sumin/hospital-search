package com.sumin.favfolders.local

import com.sumin.database.folder.FavoriteHospitalDao
import com.sumin.database.folder.FolderDao
import com.sumin.database.folder.FolderEntity
import com.sumin.favfolders.toFavoriteHospitalEntity
import com.sumin.favfolders.toFavoriteHospitalModel
import com.sumin.favfolders.toFolderEntity
import com.sumin.favfolders.toFolderModel
import com.sumin.folder_list.FavoriteHospitalModel
import com.sumin.folder_list.FolderModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface FolderLocalDataSource {
    suspend fun insertFolder(folderName: String, color: Int)
    suspend fun updateFolder(folder: FolderModel)
    suspend fun deleteFolder(vararg folder: FolderModel)
    suspend fun getAllFolders(): Flow<List<FolderModel>>

    suspend fun insertFavoriteHospital(favoriteHospitalModel: FavoriteHospitalModel)
    suspend fun deleteFavoriteHospital(vararg favoriteHospitalModel: FavoriteHospitalModel)
    suspend fun isFavoriteHospital(hospitalId: String): Boolean
    suspend fun getFavoriteHospitalsByHospitalId(hospitalId: String): Flow<List<FavoriteHospitalModel>>
    suspend fun getFavoriteHospitalsByFolderId(folderId: Long): List<FavoriteHospitalModel>
}

class FolderLocalDataSourceImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val folderDao: FolderDao,
    private val favoriteHospitalDao: FavoriteHospitalDao
) : FolderLocalDataSource {

    override suspend fun insertFolder(folderName: String, color: Int) {
        withContext(ioDispatcher) {
            folderDao.insertFolder(
                FolderEntity(folderName, color)
            )
        }
    }

    override suspend fun updateFolder(folder: FolderModel) {
        withContext(ioDispatcher) {
            folderDao.updateFolder(folder.toFolderEntity())
        }
    }

    override suspend fun deleteFolder(vararg folder: FolderModel) {
        withContext(ioDispatcher) {
            val array = folder.map { it.toFolderEntity() }.toTypedArray()
            folderDao.deleteFolder(*array)
        }
    }

    override suspend fun getAllFolders(): Flow<List<FolderModel>> {
        return withContext(ioDispatcher) {
            folderDao.getAllFolders().map {
                it.map { folderEntity -> folderEntity.toFolderModel() }
            }
        }
    }

    override suspend fun insertFavoriteHospital(favoriteHospitalModel: FavoriteHospitalModel) {
        withContext(ioDispatcher) {
            favoriteHospitalDao.insertFavoriteHospital(favoriteHospitalModel.toFavoriteHospitalEntity())
        }
    }

    override suspend fun deleteFavoriteHospital(vararg favoriteHospitalModel: FavoriteHospitalModel) {
        withContext(ioDispatcher) {
            val array = favoriteHospitalModel.map { it.toFavoriteHospitalEntity() }.toTypedArray()
            favoriteHospitalDao.deleteFavoriteHospital(*array)
        }
    }

    override suspend fun isFavoriteHospital(hospitalId: String): Boolean {
        return withContext(ioDispatcher) {
            favoriteHospitalDao.isFavoriteHospital(hospitalId)
        }
    }

    override suspend fun getFavoriteHospitalsByHospitalId(hospitalId: String): Flow<List<FavoriteHospitalModel>> {
        return withContext(ioDispatcher) {
            favoriteHospitalDao.getFavoriteHospitalsByHospitalId(hospitalId).map {
                it.map { favoriteHospitalEntity -> favoriteHospitalEntity.toFavoriteHospitalModel() }
            }
        }
    }

    override suspend fun getFavoriteHospitalsByFolderId(folderId: Long): List<FavoriteHospitalModel> {
        return withContext(ioDispatcher) {
            favoriteHospitalDao.getFavoriteHospitalsByFolderId(folderId).map {
                favoriteHospitalEntity -> favoriteHospitalEntity.toFavoriteHospitalModel()
            }
        }
    }
}