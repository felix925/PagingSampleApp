package com.example.pagingsampleapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pagingsampleapp.data.dao.AnimeDao
import com.example.pagingsampleapp.data.dao.PageKeyDao
import com.example.pagingsampleapp.data.vo.Anime
import java.util.*
import javax.inject.Inject

@ExperimentalPagingApi
class AnimeRepository @Inject constructor(
    private val animeService: AnimeService,
    private val animeDao: AnimeDao,
    private val pageKeyDao: PageKeyDao
) {
    // DBをつかわないバージョン
    fun getAnime(): Pager<Int, Anime> {
        val sessionId = UUID.randomUUID().toString()

        return Pager(
            config = PagingConfig(
                pageSize = 50,
                prefetchDistance = 100,
                enablePlaceholders = false,
            ),
            remoteMediator = AnimeMediator(
                sessionId = sessionId,
                animeService = animeService,
                animeDao = animeDao,
                pageKeyDao = pageKeyDao
            ),
            pagingSourceFactory = {
                animeDao.getAnimePagingSource(sessionId)
            },
        )
    }


}