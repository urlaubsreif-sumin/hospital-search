package com.sumin.folder_list

import kotlinx.coroutines.flow.Flow

interface FolderRepository {
    /**
     * 모든 즐겨찾기 폴더 리스트
     */
    suspend fun getFolderList(): Flow<List<FolderModel>>

    /**
     * 새 즐겨찾기 폴더 추가
     */
    suspend fun insertFolder(folderName: String, color: Int)

    /**
     * 기존 즐겨찾기 폴더 수정
     */
    suspend fun updateFolder(folder: FolderModel)

    /**
     * 기존 즐겨찾기 폴더 삭제 & 삭제될 폴더에 들어있던 좋아요 정보 삭제
     */
    suspend fun deleteFolders(vararg folder: FolderModel)

    /**
     * 즐겨찾기 폴더에 병원 추가
     */
    suspend fun insertFavoriteHospital(folder: FavoriteHospitalModel)

    /**
     * 즐겨찾기 폴더에서 병원 삭제
     */
    suspend fun deleteFavoriteHospital(vararg folder: FavoriteHospitalModel)

    /**
     * 하나 이상의 즐겨찾기 폴더에 저장되어 있는지 확인
     */
    suspend fun isFavoriteHospital(hospitalId: String): Boolean

    /**
     * 해당 병원이 어떤 즐겨찾기 폴더에 저장되어 있는지 확인
     */
    suspend fun getFavoriteHospitalsByHospitalId(hospitalId: String): Flow<List<FavoriteHospitalModel>>

    /**
     * 해당 폴더에 어떤 병원이 저장되어 있는지 확인
     */
    suspend fun getFavoriteHospitalsByFolderId(folderId: Long): List<FavoriteHospitalModel>
}