package com.example.pagingsampleapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.pagingsampleapp.data.AnimeRepository
import com.example.pagingsampleapp.data.vo.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PagingSourceViewModel @Inject constructor(
    private val animeRepository: AnimeRepository
): ViewModel() {
    // Flowの場合
    fun getReposAsFlow(): Flow<PagingData<Anime>> =
        animeRepository.getAnimes().flow

    // LiveDataの場合
    fun getReposAsLiveData(): LiveData<PagingData<Anime>> =
        animeRepository.getAnimes().liveData
}