package com.sample.catapp.catdetails.domain

import com.sample.catapp.catdetails.domain.usecase.FetchListUseCase
import com.sample.catapp.catdetails.presentation.CatBreedMapper
import com.sample.catapp.dispatcher.AppCoroutineDispatcher
import com.sample.catapp.network.networkHandler.onError
import com.sample.catapp.network.networkHandler.onException
import com.sample.catapp.utils.PagingSourceHelper
import com.sample.catdetails.CatItem
import kotlinx.coroutines.withContext

class CatListPagingSource(
    private val dispatcher: AppCoroutineDispatcher,
    private val fetchListUseCase: FetchListUseCase
) :
    PagingSourceHelper<CatItem>() {
    override suspend fun getEntities(pageNo: Int, limit: Int): List<CatItem> {
        return withContext(dispatcher.io) {
            fetchListUseCase.fromLocal(pageNo, limit)
                .map { CatBreedMapper.mapApiResponseToUiCatData(it) }
        }
    }

    override suspend fun getEntitiesFromServer(pageNo: Int, limit: Int) {
        withContext(dispatcher.io) {
            fetchListUseCase.fromRemote(pageNo, limit)
        }.onError { code, message ->
            throw Exception(message)
        }.onException {
            throw it
        }
    }
}