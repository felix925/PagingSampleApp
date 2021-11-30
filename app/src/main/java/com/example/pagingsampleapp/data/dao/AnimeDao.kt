package com.example.pagingsampleapp.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pagingsampleapp.data.vo.Anime

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: List<Anime>)

    @Query("SELECT * FROM anime WHERE sessionId = :sessionId ORDER BY sortId")
    fun getAnimePagingSource(sessionId: String): PagingSource<Int, Anime>

    @Query("DELETE FROM anime")
    suspend fun deleteAllAnime()
}