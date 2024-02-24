package com.sample.catapp.catdetails.data

import com.sample.catapp.catdetails.data.network.CatEntity
import com.sample.catapp.network.networkHandler.ApiResult
import com.sample.catdetails.CatItem

interface ICatRepository {
    suspend fun fetchCatDataRemote(
        pageNo: Int,
        pageLimit: Int
    ): ApiResult<List<CatEntity>>
    suspend fun fetchCatDataLocal(
        pageNo: Int,
        pageLimit: Int
    ): List<CatEntity>

    fun getCatDetail(catId: String): CatItem
}
