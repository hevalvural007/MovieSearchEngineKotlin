package com.hevalvural.moviesearchengine.services


import com.hevalvural.moviesearchengine.models.MovieDetails
import com.hevalvural.moviesearchengine.models.OmdbResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbAPI {

    @GET("/")
    fun getData(
        @Query("apikey") apiKey: String,
        @Query("s") keyword: String
    ): Call<OmdbResponse>

    @GET("/")
    fun getMovieDetails(
        @Query("apikey") apiKey: String,
        @Query("i") imdbId: String
    ): Call<MovieDetails>
}