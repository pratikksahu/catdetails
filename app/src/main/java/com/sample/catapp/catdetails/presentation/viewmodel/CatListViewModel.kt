package com.sample.catapp.catdetails.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.sample.catapp.catdetails.domain.CatListPagingSource
import com.sample.catapp.catdetails.domain.usecase.FetchListUseCase
import com.sample.catapp.catdetails.presentation.CatIntents
import com.sample.catapp.dispatcher.AppCoroutineDispatcher
import com.sample.catdetails.CatItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(
    private val dispatcher: AppCoroutineDispatcher,
    private val fetchListUseCase: FetchListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CatViewModelState())

    val uiState = _state
        .map(CatViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            _state.value.toUiState()
        )

    lateinit var pagingSource: CatListPagingSource

    init {
        _state.update {
            _state.value.copy(isLoading = true, isError = false)
        }
        fireIntent(CatIntents.FetchCatList)
    }


    fun fireIntent(catDetailsIntent: CatIntents) {
        when (catDetailsIntent) {
            is CatIntents.FetchCatList -> {
                fetchCatList()
            }

            is CatIntents.SelectCat -> {
                /**
                 * Nothing will happen for this intent since Detail viewmodel will handle the fetching
                 * */
            }

        }

    }

    fun fetchCatList() {
        _state.update {
            _state.value.copy(data = Pager(
                PagingConfig(
                    pageSize = PAGE_SIZE,
                    enablePlaceholders = false,
                    prefetchDistance = PAGE_SIZE
                ),
            ) {
                pagingSource = CatListPagingSource(dispatcher = dispatcher,fetchListUseCase = fetchListUseCase)
                pagingSource
            }.flow.flowOn(dispatcher.io)
                .cachedIn(viewModelScope))
        }
    }
    fun invalidate(){
        if(::pagingSource.isInitialized)
            pagingSource.invalidate()
    }
    @VisibleForTesting
    fun setLoadingFalse() {
        _state.update {
            _state.value.copy(isLoading = false)
        }
    }

    companion object {
        @VisibleForTesting
        const val PAGE_SIZE = 20

        @VisibleForTesting
        const val order = "DESC"
    }
}
