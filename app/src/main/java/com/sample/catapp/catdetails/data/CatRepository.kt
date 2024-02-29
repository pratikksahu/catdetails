package com.sample.catapp.catdetails.data

import com.sample.catapp.catdetails.presentation.toUiCatData
import com.sample.catdetails.CatItem
import com.sample.database.dao.CatDAO
import com.sample.dtomodule.catdetails.database.CatEntity
import com.sample.network.networkHandler.onSuccess
import javax.inject.Inject


class CatRepository @Inject constructor(
    private val apiService: com.sample.network.ApiService,
    private val catDAO: CatDAO
) : ICatRepository {

    override suspend fun fetchCatDataRemote(
        pageNo: Int,
        pageLimit: Int
    ): com.sample.network.networkHandler.ApiResult<List<CatEntity>> {
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

    override suspend fun getCatDetail(catId: String): CatItem = catDAO.getCatDetail(catId = catId).toUiCatData()
}





