package com.sample.catapp.catdetails.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.catapp.catdetails.data.network.CatEntity
import com.sample.catapp.catdetails.data.network.ImageInfoToStringConverter
import com.sample.catapp.catdetails.data.network.WeightToStringConverter


@Database(
    entities = [
        CatEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    WeightToStringConverter::class,
    ImageInfoToStringConverter::class
)
abstract class CatDatabase:RoomDatabase() {
    abstract fun provideCatDAO():CatDAO
}