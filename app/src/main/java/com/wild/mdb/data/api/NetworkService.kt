package com.wild.mdb.data.api

import com.wild.mdb.data.model.GenresResponse
import com.wild.mdb.utils.AppConstant.API_TOKEN
import retrofit2.http.GET
import retrofit2.http.Headers
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @Headers("accept: application/json","Authorization: Bearer $API_TOKEN")
    @GET("genre/movie/list?language=en")
    suspend fun getGenreMovies():GenresResponse
}