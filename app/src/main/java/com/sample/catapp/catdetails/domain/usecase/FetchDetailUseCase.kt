package com.sample.catapp.catdetails.domain.usecase

import com.sample.catapp.catdetails.data.CatRepository
import com.sample.catapp.catdetails.data.ICatRepository
import com.sample.catdetails.CatItem
import javax.inject.Inject

class FetchDetailUseCase @Inject constructor(private val repository: ICatRepository) {
    suspend operator fun invoke(catId:String):CatItem = repository.getCatDetail(catId)
}