package com.example.pagingsampleapp.data

import com.example.pagingsampleapp.data.vo.AnimeSearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface AnimeService {
    @GET("/v1/works")
    suspend fun getAnimes(
        @Query("access_token") token: String,
        @Query("fields") fields: List<String>?,
        @Query("filter_ids") filterIds: List<Int>?,
        @Query("filter_season") filterSeason: String?,
        @Query("filter_title") filterTitle: String?,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("sort_id") sort_id: String?,
        @Query("sort_season") sortSeason: String?,
        @Query("sort_watchers_count") sortWatcher: String?
    ): Response<AnimeSearchResult>
}

class AnimeApi @Inject constructor(
    private val service: AnimeService
) : AnimeService {

    override suspend fun getAnimes(
        token: String,
        fields: List<String>?,
        filterIds: List<Int>?,
        filterSeason: String?,
        filterTitle: String?,
        page: Int?,
        perPage: Int?,
        sort_id: String?,
        sortSeason: String?,
        sortWatcher: String?
    ): Response<AnimeSearchResult> =
        service.getAnimes(
            token,
            fields,
            filterIds,
            filterSeason,
            filterTitle,
            page,
            perPage,
            sort_id,
            sortSeason,
            sortWatcher,
        )

}