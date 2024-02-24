package com.sample.catapp.catdetails.domain

import com.sample.catapp.catdetails.domain.usecase.FetchListUseCase
import com.sample.catapp.catdetails.presentation.mapApiResponseToUiCatData
import com.sample.catapp.dispatcher.AppCoroutineDispatcher
import com.sample.catapp.network.networkHandler.onError
import com.sample.catapp.network.networkHandler.onException
import com.sample.catapp.utils.PagingSourceHelper
import com.sample.catdetails.CatItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CatListPagingSource(
    private val dispatcher: AppCoroutineDispatcher,
    private val pageSize:Int,
    private val fetchListUseCase: FetchListUseCase
) :
    PagingSourceHelper<CatItem>() {
    override fun getPageSize(): Int  = pageSize

    override suspend fun getEntities(pageNo: Int, limit: Int): List<CatItem> {
        return withContext(dispatcher.io) {
            fetchListUseCase.fromLocal(pageNo, limit)
                .map { it.mapApiResponseToUiCatData() }
        }
    }

    override suspend fun getEntitiesFromServer(pageNo: Int, limit: Int) {
        withContext(dispatcher.io) {
            delay(5000)
            fetchListUseCase.fromRemote(pageNo, limit)
        }.onError { code, message ->
            throw Exception(message)
        }.onException {
            throw it
        }
    }
}