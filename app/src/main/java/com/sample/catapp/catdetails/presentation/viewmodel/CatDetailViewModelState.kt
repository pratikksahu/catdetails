package com.sample.catapp.catdetails.presentation.viewmodel

import androidx.paging.PagingData
import com.sample.catapp.catdetails.domain.CatListPagingSource
import com.sample.catdetails.CatItem
import com.sample.catdetails.states.CatDetailUiState
import com.sample.catdetails.states.CatUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class CatDetailViewModelState(
    val data: CatItem? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
) {
    fun toUiState(): CatDetailUiState {
        return if (isError)
            CatDetailUiState.NoCatData(
                isLoading = false,
                errorMessage = errorMessage,
                isError = true
            )
        else
            data?.let {
                CatDetailUiState.CatData(
                    data = data,
                    isLoading = isLoading,
                    errorMessage = errorMessage,
                    isError = isError
                )
            } ?: CatDetailUiState.NoCatData(
                isLoading = true,
                errorMessage = null,
                isError = false
            )

    }

}
