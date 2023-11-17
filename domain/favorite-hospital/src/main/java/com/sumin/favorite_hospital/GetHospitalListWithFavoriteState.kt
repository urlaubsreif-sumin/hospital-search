package com.sumin.favorite_hospital

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.sumin.folder_list.FolderRepository
import com.sumin.list.hospital.Constants
import com.sumin.list.hospital.HospitalModel
import com.sumin.list.hospital.HospitalQuery
import com.sumin.list.hospital.HospitalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * 전체 병원 리스트 + 1개 폴더 이상 즐겨찾기 여부 체크
 */
class GetHospitalListWithFavoriteState @Inject constructor(
    private val folderRepository: FolderRepository,
    private val hospitalRepository: HospitalRepository,
    private val defaultDispatcher: CoroutineContext
) {
    suspend operator fun invoke(query: HospitalQuery): Flow<PagingData<HospitalModel>> {
        return withContext(defaultDispatcher) {
            Pager(
                PagingConfig(pageSize = Constants.PAGE_SIZE)
            ) {
                hospitalRepository.getHospitalListByQuery(query)
            }.flow
                .distinctUntilChanged()
                .map { pagingData ->
                    pagingData.map { hospital ->
                        hospital.copy(
                            isFavorite = folderRepository.isFavoriteHospital(hospital.id)
                        )
                    }
                }
        }
    }
}