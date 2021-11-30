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
        @Query("page") page: Int?,
        @Query("per_page") pageSize: Int?,
    ): Response<AnimeSearchResult>
}

class AnimeApi @Inject constructor(
    private val service: AnimeService
) : AnimeService {

    override suspend fun getAnimes(
        token: String,
        page: Int?,
        pageSize: Int?
    ): Response<AnimeSearchResult> =
        service.getAnimes(
            token = token,
            page = page,
            pageSize = pageSize,
        )

}