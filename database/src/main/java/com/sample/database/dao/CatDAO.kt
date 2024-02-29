package com.sample.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.sample.dtomodule.catdetails.database.CatEntity

@Dao
interface CatDAO {

    @Upsert
    fun insertAll(data:List<CatEntity>)

    @Query("Select * from ${CatEntity.TABLE_NAME} limit :limit offset :offset")
    fun getCats(offset: Int,limit:Int):List<CatEntity>

    @Query("Select * from ${CatEntity.TABLE_NAME} where id = :catId")
    suspend fun getCatDetail(catId:String): CatEntity
}