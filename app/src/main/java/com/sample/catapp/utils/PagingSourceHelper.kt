package com.sample.catapp.utils

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class PagingSourceHelper<T : Any> : PagingSource<Int, T>() {
    abstract fun getPageSize():Int
    abstract suspend fun getEntities(pageNo: Int, limit: Int): List<T>
    abstract suspend fun getEntitiesFromServer(pageNo: Int, limit: Int)
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: 0

        return try {
            var entities = getEntities(
                page,
                getPageSize()
            )
            if (entities.isEmpty() || entities.size < getPageSize()) {
                getEntitiesFromServer(
                    page,
                    getPageSize()
                )
                entities = getEntities(
                    page,
                    getPageSize()
                )
            }
            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            Log.d("PAGING_ERROR", e.message.toString())
            LoadResult.Error(e)
        }
    }
}