package com.sample.catapp.catdetails.presentation.viewmodel

import androidx.paging.PagingData
import com.sample.catapp.catdetails.domain.CatListPagingSource
import com.sample.catdetails.CatItem
import com.sample.catdetails.states.CatUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class CatViewModelState(
    val data: Flow<PagingData<CatItem>> = flowOf(),
    val pagingSource:CatListPagingSource? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
) {
    fun toUiState(): CatUiState {
        return CatUiState.CatData(
            data = data,
            isLoading = isLoading,
            errorMessage = errorMessage,
            isError = isError
        )

    }

}
