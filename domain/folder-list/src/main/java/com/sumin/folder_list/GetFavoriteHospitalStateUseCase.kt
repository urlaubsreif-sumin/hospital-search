package com.sumin.folder_list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * 전체 즐겨찾기 폴더 + 각 즐겨찾기 여부 체크
 */
class GetFavoriteHospitalStateUseCase @Inject constructor(
    private val folderRepository: FolderRepository,
    private val defaultDispatcher: CoroutineContext
) {
    suspend operator fun invoke(hospialId: String): Flow<List<FolderModel>> {
        return withContext(defaultDispatcher) {
            val folderListFlow = folderRepository.getFolderList()
            val favoriteHospitalListFlow =
                folderRepository.getFavoriteHospitalsByHospitalId(hospialId)

            folderListFlow.combine(favoriteHospitalListFlow) { folderList, favoriteHospitalList ->

                folderList.map { folder ->
                    val isFavorite = favoriteHospitalList.firstOrNull { favoriteHospital ->
                        favoriteHospital.folderId == folder.id
                    }?.run { true } ?: false

                    folder.copy(
                        isChecked = isFavorite
                    )
                }
            }
        }

    }
}