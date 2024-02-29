package com.sample.catapp.di
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.sample.catapp.BuildConfig
import com.sample.catapp.database.CatDatabase
import com.sample.database.dao.CatDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context): CatDatabase {
        val rdb = Room.databaseBuilder(
            context,
            CatDatabase::class.java,
            "CatDatabase"
        ).apply {
            if (BuildConfig.DEBUG)
                setQueryCallback({ sqlQuery, bindArgs ->
                    Log.d(
                        "ROOM",
                        "SQL Query: $sqlQuery SQL Args: $bindArgs"
                    )
                }, Executors.newWorkStealingPool())
            setQueryExecutor(Executors.newWorkStealingPool())
            setTransactionExecutor(Executors.newWorkStealingPool())
        }.build()
        return rdb
    }

    @Provides
    @Singleton
    fun provideCatDAO(catDatabase: CatDatabase): CatDAO = catDatabase.provideCatDAO()
}