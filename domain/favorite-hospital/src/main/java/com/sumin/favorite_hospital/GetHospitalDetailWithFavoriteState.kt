package com.sumin.favorite_hospital

import com.sumin.folder_list.FolderRepository
import com.sumin.list.hospital.HospitalDetailModel
import com.sumin.list.hospital.HospitalRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * 병원 상세 정보 + 1개 폴더 이상 즐겨찾기 여부 체크
 */
class GetHospitalDetailWithFavoriteState @Inject constructor(
    private val folderRepository: FolderRepository,
    private val hospitalRepository: HospitalRepository,
    private val defaultDispatcher: CoroutineContext
) {
    suspend operator fun invoke(hospitalId: String): HospitalDetailModel {
        return withContext(defaultDispatcher) {
            val getHospitalDetail = async { hospitalRepository.getHospitalDetailById(hospitalId) }
            val getFavoriteHospital = async { folderRepository.isFavoriteHospital(hospitalId) }

            val hospitalDetail = getHospitalDetail.await()
            val favoriteHospital = getFavoriteHospital.await()

            hospitalDetail.copy(
                isFavorite = favoriteHospital
            )
        }
    }
}