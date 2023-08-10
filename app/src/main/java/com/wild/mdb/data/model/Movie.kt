package com.wild.mdb.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("poster_path")
    val posterPath: String = "",
)
