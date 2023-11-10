package com.sumin.favfolders

import com.sumin.database.folder.FavoriteHospitalEntity
import com.sumin.database.folder.FolderEntity
import com.sumin.folder_list.FavoriteHospitalModel
import com.sumin.folder_list.FolderModel


fun FolderModel.toFolderEntity(): FolderEntity {
    return FolderEntity(
        id = id,
        name = name,
        color = color
    )
}

fun FolderEntity.toFolderModel(): FolderModel {
    return FolderModel(
        id = id,
        name = name,
        color = color
    )
}

fun FavoriteHospitalModel.toFavoriteHospitalEntity(): FavoriteHospitalEntity {
    return FavoriteHospitalEntity(
        id = id,
        folderId = folderId,
        hospitalId = hospitalId
    )
}

fun FavoriteHospitalEntity.toFavoriteHospitalModel(): FavoriteHospitalModel {
    return FavoriteHospitalModel(
        id = id,
        folderId = folderId,
        hospitalId = hospitalId
    )
}