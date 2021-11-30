package com.example.pagingsampleapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pagingsampleapp.data.vo.PageKey

@Dao
interface PageKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(key: PageKey)

    @Query("SELECT * FROM page_key WHERE sessionId = :sessionId ORDER BY id DESC LIMIT 1")
    suspend fun getLastKey(sessionId: String): PageKey?

    @Query("DELETE FROM page_key")
    suspend fun deleteAllPageKey()
}