package com.example.pagingsampleapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pagingsampleapp.data.vo.Anime
import javax.inject.Inject

class AnimeRepository @Inject constructor(
    private val service: AnimeService,
) {
    // DBをつかわないバージョン
    fun getAnimes(): Pager<Int, Anime> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 80,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                AnimePagingSource(
                    service = service
                )
            },
        )

}