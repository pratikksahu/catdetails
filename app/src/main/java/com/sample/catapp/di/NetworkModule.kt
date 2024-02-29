package com.sample.catapp.di

import com.sample.network.ApiService
import com.sample.network.networkHandler.BasicAuthInterceptor
import com.sample.network.networkHandler.ResultCallAdapterFactory
import com.sample.network.utils.BASE_URL
import com.sample.network.utils.CONNECT_TIMEOUT_IN_SECONDS
import com.sample.network.utils.READ_TIMEOUT_IN_SECONDS
import com.sample.network.utils.WRITE_TIMEOUT_IN_SECONDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(com.sample.network.utils.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(com.sample.network.networkHandler.ResultCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .connectTimeout(CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS) // Connection timeout
            .readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS) // Read timeout
            .writeTimeout(WRITE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS) // Write timeout
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): com.sample.network.ApiService {
        return retrofit.create(com.sample.network.ApiService::class.java)
    }

}
