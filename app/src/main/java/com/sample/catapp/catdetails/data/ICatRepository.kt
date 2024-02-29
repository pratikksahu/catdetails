package com.sample.catapp.catdetails.data

import com.sample.catdetails.CatItem
import com.sample.dtomodule.catdetails.database.CatEntity
import com.sample.network.networkHandler.ApiResult

interface ICatRepository {
    suspend fun fetchCatDataRemote(
        pageNo: Int,
        pageLimit: Int
    ): ApiResult<List<CatEntity>>
    suspend fun fetchCatDataLocal(
        pageNo: Int,
        pageLimit: Int
    ): List<CatEntity>

    suspend fun getCatDetail(catId: String): CatItem
}
