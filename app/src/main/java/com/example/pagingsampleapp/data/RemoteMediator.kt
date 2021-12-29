package com.example.pagingsampleapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pagingsampleapp.data.dao.AnimeDao
import com.example.pagingsampleapp.data.dao.PageKeyDao
import com.example.pagingsampleapp.data.vo.Anime
import com.example.pagingsampleapp.data.vo.AnimeSearchResult
import com.example.pagingsampleapp.data.vo.PageKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import java.util.*

@ExperimentalPagingApi
class AnimeMediator (
    private val sessionId: String,
    private val animeService: AnimeService,
    private val animeDao: AnimeDao,
    private val pageKeyDao: PageKeyDao,
): RemoteMediator<Int, Anime>() {
    private val MINIMUM_PAGE_SIZE = 0

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Anime>): MediatorResult {

        return try {
            withContext(Dispatchers.IO) {
                val page = when (loadType) {
                    LoadType.REFRESH -> {
                        MINIMUM_PAGE_SIZE
                    }
                    LoadType.PREPEND -> {
                        return@withContext MediatorResult.Success(true)
                    }
                    LoadType.APPEND -> {
                        val remoteKey = pageKeyDao.getLastKey(sessionId)
                        remoteKey?.nextKey ?: MINIMUM_PAGE_SIZE
                    }
                }

                if (loadType == LoadType.REFRESH || page == MINIMUM_PAGE_SIZE) {
                    animeDao.deleteAllAnime()
                    pageKeyDao.deleteAllPageKey()
                }

                val response: Response<AnimeSearchResult> = animeService.getAnimes(
                    token = "取得したトークン", page = page, pageSize = state.config.pageSize
                )

                val animeList = response.body()?.animeList ?: listOf()
                val endOfPage = response.isSuccessful && animeList.isEmpty()
                val nextKey = if(endOfPage) null else page + 1

                pageKeyDao.insertKey(
                    PageKey(
                        id = 0,
                        sessionId = sessionId,
                        nextKey = nextKey
                    )
                )
                animeDao.insertAnime(animeList.map { it.copy(sessionId = sessionId) })

                return@withContext MediatorResult.Success(endOfPaginationReached = endOfPage)
            }
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}