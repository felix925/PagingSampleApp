package com.example.pagingsampleapp.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "page_key")
data class PageKey(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val sessionId: String,
    val nextKey: Int?
)