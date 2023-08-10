package com.wild.mdb.data.repository

import com.wild.mdb.data.api.NetworkService
import com.wild.mdb.data.model.Genre
import com.wild.mdb.data.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServerRepository @Inject constructor(private val networkService: NetworkService) {
    fun getGenres(): Flow<List<Genre>>{
        return flow {
            emit(networkService.getGenreMovies())
        }.map {
            it.genres
        }
    }

    fun getDiscoverMovie(page: Int, genreId: Int): Flow<List<Movie>>{
        return flow {
            emit(networkService.getDiscoverMovie(page,genreId))
        }.map {
            it.result
        }
    }
}