package com.wild.mdb.data.model

import com.google.gson.annotations.SerializedName

data class DetailMovie(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("tagline")
    val tagline: String = "",
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("revenue")
    val revenue: Int = 0,
    @SerializedName("runtime")
    val runtime: Int = 0,
)
