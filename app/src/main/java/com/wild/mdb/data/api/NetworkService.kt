package com.wild.mdb.data.api

import com.wild.mdb.data.model.DetailMovie
import com.wild.mdb.data.model.DiscoverResponse
import com.wild.mdb.data.model.GenresResponse
import com.wild.mdb.utils.AppConstant.API_TOKEN
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @Headers("accept: application/json","Authorization: Bearer $API_TOKEN")
    @GET("genre/movie/list?language=en")
    suspend fun getGenreMovies():GenresResponse

    @Headers("accept: application/json","Authorization: Bearer $API_TOKEN")
    @GET("discover/movie")
    suspend fun getDiscoverMovie(@Query("page") page: Int, @Query("with_genres") genreId: Int):DiscoverResponse

    @Headers("accept: application/json","Authorization: Bearer $API_TOKEN")
    @GET("movie/{id}")
    suspend fun getDetailMovie(@Path("id")id: Int):DetailMovie
}