package com.sample.catapp.catdetails.presentation

import com.sample.catdetails.CatItem

sealed class CatIntents {
    data object FetchCatList : CatIntents()
    data class SelectCat(val obj: CatItem) : CatIntents()
}
