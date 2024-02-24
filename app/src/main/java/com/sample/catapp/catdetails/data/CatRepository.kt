package com.sample.catapp.catdetails.data

import com.sample.catapp.catdetails.data.db.CatDAO
import com.sample.catapp.catdetails.data.network.CatEntity
import com.sample.catapp.catdetails.presentation.mapApiResponseToUiCatData
import com.sample.catapp.network.ApiService
import com.sample.catapp.network.networkHandler.ApiResult
import com.sample.catapp.network.networkHandler.onSuccess
import com.sample.catdetails.CatItem
import javax.inject.Inject


class CatRepository @Inject constructor(
    private val apiService: ApiService,
    private val catDAO: CatDAO
) : ICatRepository {

    override suspend fun fetchCatDataRemote(
        pageNo: Int,
        pageLimit: Int
    ): ApiResult<List<CatEntity>> {
        return apiService.fetchData(limit = pageLimit, page = pageNo).onSuccess {
            catDAO.insertAll(it)
        }
    }

    override suspend fun fetchCatDataLocal(
        pageNo: Int,
        pageLimit: Int
    ): List<CatEntity> {
        return catDAO.getCats(pageNo * pageLimit, pageLimit)
    }

    override suspend fun getCatDetail(catId: String): CatItem = catDAO.getCatDetail(catId = catId).mapApiResponseToUiCatData()
}





