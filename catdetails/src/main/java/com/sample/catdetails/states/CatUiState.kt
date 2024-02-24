package com.sample.catdetails.states

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.sample.catdetails.CatItem
import kotlinx.coroutines.flow.Flow


sealed interface CatUiState {
    val isLoading: Boolean
    val errorMessage: String?
    val isError: Boolean

    data class NoCatData(
        override val isLoading: Boolean,
        override val errorMessage: String?,
        override val isError: Boolean
    ) : CatUiState

    @Stable
    data class CatData(
        val data: Flow<PagingData<CatItem>>,
        override val isLoading: Boolean,
        override val errorMessage: String?,
        override val isError: Boolean
    ) : CatUiState
}