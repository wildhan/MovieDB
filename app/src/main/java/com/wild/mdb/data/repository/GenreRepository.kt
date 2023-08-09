package com.wild.mdb.data.repository

import com.wild.mdb.data.api.NetworkService
import com.wild.mdb.data.model.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRepository @Inject constructor(private val networkService: NetworkService) {
    fun getGenres(): Flow<List<Genre>>{
        return flow {
            emit(networkService.getGenreMovies())
        }.map {
            it.genres
        }
    }
}