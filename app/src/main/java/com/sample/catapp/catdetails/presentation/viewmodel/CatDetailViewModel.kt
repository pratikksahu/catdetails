package com.sample.catapp.catdetails.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.catapp.CatsDestinations
import com.sample.catapp.catdetails.domain.usecase.FetchDetailUseCase
import com.sample.catapp.dispatcher.AppCoroutineDispatcher
import com.sample.catdetails.CatItem
import com.sample.catdetails.states.CatDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dispatcher: AppCoroutineDispatcher,
    private val fetchDetailUseCase: FetchDetailUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(
        CatDetailViewModelState()
    )

    val uiState = _state
        .map(CatDetailViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            _state.value.toUiState()
        )

    init {
        _state.update {
            _state.value.copy(isLoading = true, isError = false)
        }
        savedStateHandle.getLiveData<String>(CatsDestinations.CAT_DETAIL.getParam())
            .observeForever {
                it?.let {
                    getDetail(catId = it)
                }
            }
    }

    private fun getDetail(catId: String) {
        viewModelScope.launch(dispatcher.io) {
            runCatching {
                val catDetail = fetchDetailUseCase(catId)
                _state.update {
                    _state.value.copy(data = catDetail)
                }
            }.onFailure {
                _state.update {
                    _state.value.copy(isError = true, errorMessage = it.errorMessage)
                }
            }
        }
    }
}