package com.hevalvural.moviesearchengine.models

import com.google.gson.annotations.SerializedName

data class OmdbResponse(
    @SerializedName("Search") val search: List<Movie>,
    val totalResults: String,
    @SerializedName("Response") val response: String
)
