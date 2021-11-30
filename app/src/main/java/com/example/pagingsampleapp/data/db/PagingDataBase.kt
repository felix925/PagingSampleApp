package com.example.pagingsampleapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pagingsampleapp.data.converter.AnimeConverter
import com.example.pagingsampleapp.data.dao.AnimeDao
import com.example.pagingsampleapp.data.dao.PageKeyDao
import com.example.pagingsampleapp.data.vo.Anime
import com.example.pagingsampleapp.data.vo.PageKey

@TypeConverters(AnimeConverter::class)
@Database(
    entities = [Anime::class, PageKey::class],
    version = 1,
    exportSchema = false
)
abstract class PagingDataBase : RoomDatabase() {
    abstract fun getAnimeDao(): AnimeDao
    abstract fun getPageKeyDao(): PageKeyDao
}