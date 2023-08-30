package com.sumin.data.hospitals

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sumin.data.hospitals.remote.HospitalApi
import com.sumin.data.hospitals.remote.HospitalApiModel
import com.sumin.list.hospital.HospitalQuery
import com.sumin.list.hospital.Constants
import com.sumin.list.hospital.HospitalDetailModel
import com.sumin.list.hospital.HospitalModel

class HospitalPagingSource(
    private val backend: HospitalApi,
    private val query: HospitalQuery,
    private val getHospitalModels: suspend (List<HospitalApiModel>) -> Unit
) : PagingSource<Int, HospitalModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HospitalModel> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = backend.getHospitalList(
                pageNo = nextPageNumber,
                numOfRows = Constants.PAGE_SIZE,
                sidoCode = query.sidoCd,
                sgguCode = query.sgguCd,
                emdongNumber = query.emdongNm,
                hospitalName = query.yadmNm,
                zipCode = query.zipCd,
                clCode = query.clCd,
                dgsbjtCode = query.dgsbjtCd,
                serviceKey = BuildConfig.API_KEY
            )

            val items = response.body?.hospitalListApiModel?.item
                ?: emptyList()
            getHospitalModels(items)

            return LoadResult.Page(
                data = items.map { it.toHospitalModel() },
                prevKey = null,
                nextKey = response.body?.pageNo?.plus(1)
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, HospitalModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}