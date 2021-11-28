package com.example.pagingsampleapp.data

import android.content.res.Resources
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingsampleapp.data.vo.Anime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class AnimePagingSource(
    private val service: AnimeService,
) : PagingSource<Int, Anime>() {
    // APIのpage指定の最小値
    private val FIRST_INDEX = 0

    // APIの1チャンクあたりの取得データ数
    private val PAGE_SIZE = 30

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        val position = state.anchorPosition ?: return null
        val prevKey = state.closestPageToPosition(position)?.prevKey
        val nextKey = state.closestPageToPosition(position)?.nextKey

        return prevKey?.plus(1) ?: nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val position = params.key ?: FIRST_INDEX

        return try {
            withContext(Dispatchers.IO) {
                val result = service.getAnimes(
                    token = "",
                    fields = null,
                    filterIds = null,
                    filterSeason = null,
                    filterTitle = null,
                    page = position,
                    perPage = PAGE_SIZE,
                    sort_id = null,
                    sortSeason = null,
                    sortWatcher = null
                )

                val repositories = result.body()?.animeList
                val nextKey = if (repositories.isNullOrEmpty()) null else position + 1

                return@withContext LoadResult.Page(
                    data = repositories ?: listOf(),
                    prevKey = if (position >= FIRST_INDEX) position - 1 else FIRST_INDEX,
                    nextKey = nextKey
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}