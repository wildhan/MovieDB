package com.wild.mdb.data.model

import com.google.gson.annotations.SerializedName

data class DiscoverResponse (
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val result: List<Movie>,
    @SerializedName("total_pages")
    val totalPage: Int = 0,
    @SerializedName("total_result")
    val totalResult: Int = 0,
)