package com.sample.catapp.catdetails.data.db

import androidx.compose.ui.geometry.Offset
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.sample.catapp.catdetails.data.network.CatEntity

@Dao
interface CatDAO {

    @Upsert
    fun insertAll(data:List<CatEntity>)

    @Query("Select * from ${CatEntity.TABLE_NAME} limit :limit offset :offset")
    fun getCats(offset: Int,limit:Int):List<CatEntity>

    @Query("Select * from ${CatEntity.TABLE_NAME} where id = :catId")
    suspend fun getCatDetail(catId:String):CatEntity
}