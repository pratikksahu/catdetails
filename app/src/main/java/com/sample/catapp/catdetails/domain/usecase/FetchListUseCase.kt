package com.sample.catapp.catdetails.domain.usecase

import com.sample.catapp.catdetails.data.ICatRepository
import com.sample.dtomodule.catdetails.database.CatEntity
import com.sample.network.networkHandler.ApiResult
import javax.inject.Inject

class FetchListUseCase @Inject constructor(private val repository: ICatRepository) {

    suspend fun fromRemote(
        pageNo: Int,
        pageLimit: Int
    ): ApiResult<List<CatEntity>> =
        repository.fetchCatDataRemote(pageNo, pageLimit)

    suspend fun fromLocal(
        pageNo: Int,
        pageLimit: Int
    ): List<CatEntity> =
        repository.fetchCatDataLocal(pageNo, pageLimit)

}
