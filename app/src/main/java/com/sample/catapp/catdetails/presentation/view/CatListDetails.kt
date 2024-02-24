package com.sample.catapp.catdetails.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.sample.catdetails.view.catdetails.DetailsOfCat
import com.sample.catdetails.view.catlist.ListOfCats
import com.sample.catapp.CatsDestinations
import com.sample.catapp.catdetails.presentation.CatIntents
import com.sample.catapp.catdetails.presentation.viewmodel.CatDetailViewModel
import com.sample.catapp.catdetails.presentation.viewmodel.CatListViewModel
import com.sample.catdetails.states.CatDetailUiState
import com.sample.catdetails.view.catlist.CatShimmerLoader

@Composable
fun CatList(navController: NavHostController, viewModel: CatListViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ListOfCats(uiState = uiState,
        retry = {
            viewModel.invalidate()
        },
        onItemClick = {
            navController.navigate(CatsDestinations.CAT_DETAIL.createRoute(it.id))
        })
}

@Composable
fun CatDetails(navController: NavHostController, viewModel: CatDetailViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (uiState) {
        is CatDetailUiState.CatData -> DetailsOfCat(catItem = (uiState as CatDetailUiState.CatData).data) {
            navController.popBackStack()
        }

        is CatDetailUiState.NoCatData -> CatShimmerLoader()
    }
}
