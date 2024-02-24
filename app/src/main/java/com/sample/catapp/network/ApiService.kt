package com.sample.catapp.network

import com.sample.catapp.catdetails.data.network.CatEntity
import com.sample.catapp.network.networkHandler.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("breeds")
    suspend fun fetchData(
        @Query("limit") limit:Int,
        @Query("page") page:Int,
    ): ApiResult<List<CatEntity>>
}
