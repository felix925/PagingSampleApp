package com.example.pagingsampleapp.di

import android.content.Context
import androidx.room.Room
import com.example.pagingsampleapp.data.AnimeService
import com.example.pagingsampleapp.data.dao.AnimeDao
import com.example.pagingsampleapp.data.dao.PageKeyDao
import com.example.pagingsampleapp.data.db.PagingDataBase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(gson: Gson, client: OkHttpClient ): Retrofit =
        Retrofit.Builder().client(client)
            .baseUrl("https://api.annict.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideReposService(retrofit: Retrofit): AnimeService =
        retrofit.create(AnimeService::class.java)

    @Provides
    @Singleton
    fun providePagingDataBase(
        @ApplicationContext context: Context
    ): PagingDataBase = Room.inMemoryDatabaseBuilder(
        context,
        PagingDataBase::class.java
    ).build()

    @Provides
    @Singleton
    fun provideAnimeDao(
        dataBase: PagingDataBase
    ): AnimeDao = dataBase.getAnimeDao()

    @Provides
    @Singleton
    fun providePageKeyDao(
        dataBase: PagingDataBase
    ): PageKeyDao = dataBase.getPageKeyDao()
}