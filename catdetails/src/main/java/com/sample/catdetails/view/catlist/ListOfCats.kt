package com.sample.catdetails.view.catlist

import androidx.compose.animation.AnimatedContent
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
import androidx.compose.material3.Button
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
    retry: () -> Unit,
    onItemClick: (CatItem) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is CatUiState.NoCatData -> {
                if (uiState.isLoading) {
                    Column {
                        repeat(6){
                            CatShimmerLoader()
                        }
                    }
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
                        retry = retry,
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
    retry:() -> Unit,
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
    AnimatedContent(targetState = error.isNullOrEmpty(), label = "") {
        if(it){
            LazyVerticalGrid(
                columns = GridCells.Fixed(gridCell),
                contentPadding = PaddingValues(16.dp),
                modifier = modifier
            ) {
                if (catItems.loadState.refresh == LoadState.Loading) {
                    items(6){
                        CatShimmerLoader()
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
                    items(6){
                        CatShimmerLoader()
                    }
                }
            }
        }else{
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 10.dp),
                        text = error ?: ""
                    )
                    Button(onClick = retry) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }
}
