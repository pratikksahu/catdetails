package com.sample.catapp.database
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.database.dao.CatDAO
import com.sample.dtomodule.catdetails.database.CatEntity
import com.sample.dtomodule.catdetails.database.ImageInfoToStringConverter
import com.sample.dtomodule.catdetails.database.WeightToStringConverter


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
    abstract fun provideCatDAO(): CatDAO
}