package com.sample.catdetails.view.catlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.sample.catdetails.CatItem
import com.sample.catdetails.R
import com.sample.catdetails.states.CatUiState
import com.sample.catdetails.view.common.AppTopBar
import com.sample.catdetails.view.common.CustomProgressBar

@Composable
fun ListOfCats(
    uiState: CatUiState,
    onItemClick: (CatItem) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is CatUiState.NoCatData -> {
                if (uiState.isLoading) {
                    CustomProgressBar(Modifier.size(24.dp))
                } else {
                    Text(
                        text = uiState.errorMessage
                            ?: stringResource(id = R.string.no_data_available)
                    )
                }
            }

            is CatUiState.CatData -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    // AppTopBar
                    AppTopBar(
                        text = stringResource(id = R.string.cat_list),
                        isBackButtonRequired = false,
                        onBackClicked = { }
                    )
                    val catItems = uiState.data.collectAsLazyPagingItems()
                    CatList(modifier = Modifier.fillMaxSize(),
                        gridCell = 2,
                        catItems = catItems,
                        onItemClick = onItemClick
                    )
                }
            }
        }
    }
}

@Composable
fun CatList(
    modifier: Modifier,
    gridCell: Int = 2,
    catItems: LazyPagingItems<CatItem>,
    onItemClick: (CatItem) -> Unit
) {
    val error by remember(catItems.loadState) {
        derivedStateOf {
            if (catItems.loadState.refresh is LoadState.Error) {
                (catItems.loadState.refresh as LoadState.Error).error.message
            } else if (catItems.loadState.append is LoadState.Error) {
                (catItems.loadState.append as LoadState.Error).error.message
            } else if (catItems.loadState.prepend is LoadState.Error) {
                (catItems.loadState.prepend as LoadState.Error).error.message
            } else {
                null
            }
        }
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(gridCell),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        error?.let {
            item(span = { GridItemSpan(gridCell) }) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    text = it
                )
            }
        }
        if (catItems.loadState.refresh == LoadState.Loading) {
            item(span = { GridItemSpan(gridCell) }) {
                CustomProgressBar(Modifier.size(24.dp))
            }
        }
        items(
            count = catItems.itemCount,
            key = catItems.itemKey { it.id },
            contentType = catItems.itemContentType { item -> item }) {
            catItems[it]?.let{
                CatItemCard(
                    catItem = it,
                    onItemClick = onItemClick
                )
            }
        }
        if (catItems.loadState.append == LoadState.Loading) {
            item(span = { GridItemSpan(gridCell) }) {
                CustomProgressBar(Modifier.size(24.dp))
            }
        }
    }
}
