package com.sample.catapp

sealed class CatsDestinations(val route: String) {
    data object CatsList : CatsDestinations("cats_list")
    data object CatDetail : CatsDestinations("cat_detail/{catId}") {
        fun createRoute(catId: String) = "cat_detail/$catId"
        fun getParam() = "catId"
    }
}

