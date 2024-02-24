package com.sample.catapp

sealed class CatsDestinations(val route: String) {
    data object CATS_LIST : CatsDestinations("cats_list")
    data object CAT_DETAIL : CatsDestinations("cat_detail/{catId}") {
        fun createRoute(catId: String) = "cat_detail/$catId"
        fun getParam() = "catId"
    }
}

