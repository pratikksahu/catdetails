package com.sample.network

import com.sample.dtomodule.catdetails.database.CatEntity
import com.sample.network.networkHandler.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("breeds")
    suspend fun fetchData(
        @Query("limit") limit:Int,
        @Query("page") page:Int,
    ): ApiResult<List<CatEntity>>
}
