package com.example.pagingsampleapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.pagingsampleapp.data.AnimeRepository
import com.example.pagingsampleapp.data.vo.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class PagingSourceViewModel @Inject constructor(
    private val animeRepository: AnimeRepository
): ViewModel() {
    // Flowの場合
    fun getAnimesAsFlow(): Flow<PagingData<Anime>> =
        animeRepository.getAnime().flow.cachedIn(viewModelScope)

    // LiveDataの場合
    fun getAnimesAsLiveData(): LiveData<PagingData<Anime>> =
        animeRepository.getAnime().liveData
}