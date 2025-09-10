package com.hevalvural.moviesearchengine.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hevalvural.moviesearchengine.BuildConfig
import com.hevalvural.moviesearchengine.models.Movie
import com.hevalvural.moviesearchengine.models.MovieDetails
import com.hevalvural.moviesearchengine.models.OmdbResponse
import com.hevalvural.moviesearchengine.services.OmdbAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class MovieUiState(
    val movies: List<Movie> = emptyList(),
    val movieDetails: MovieDetails? = null,
    val isLoading: Boolean = false,
    val isLoadingDetails: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val currentScreen: Screen = Screen.SEARCH
)

enum class Screen {
    SEARCH, LIST, DETAILS
}

class MovieViewModel : ViewModel() {



    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.omdbapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val omdbAPI = retrofit.create(OmdbAPI::class.java)

    fun searchMovies(query: String) {
        if (query.isBlank()) return

        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error = null,
            searchQuery = query
        )

        viewModelScope.launch {
            try {
                val response = omdbAPI.getData(BuildConfig.OMDB_API_KEY, query)
                response.enqueue(object : retrofit2.Callback<OmdbResponse> {
                    override fun onResponse(
                        call: retrofit2.Call<OmdbResponse>,
                        response: retrofit2.Response<OmdbResponse>
                    ) {
                        if (response.isSuccessful) {
                            val movies = response.body()?.search ?: emptyList()
                            _uiState.value = _uiState.value.copy(
                                movies = movies,
                                isLoading = false,
                                error = null,
                                currentScreen = Screen.LIST
                            )
                        } else {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = "Failed to fetch movies: ${response.code()}"
                            )
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<OmdbResponse>, t: Throwable) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = "Network error: ${t.message}"
                        )
                    }
                })
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Error: ${e.message}"
                )
            }
        }
    }

    fun clearMovies() {
        _uiState.value = _uiState.value.copy(
            movies = emptyList(),
            movieDetails = null,
            error = null,
            currentScreen = Screen.SEARCH
        )
    }

    fun getMovieDetails(imdbId: String) {
        _uiState.value = _uiState.value.copy(
            isLoadingDetails = true,
            error = null
        )

        viewModelScope.launch {
            try {
                val response = omdbAPI.getMovieDetails(BuildConfig.OMDB_API_KEY, imdbId)
                response.enqueue(object : retrofit2.Callback<MovieDetails> {
                    override fun onResponse(
                        call: retrofit2.Call<MovieDetails>,
                        response: retrofit2.Response<MovieDetails>
                    ) {
                        if (response.isSuccessful) {
                            val details = response.body()
                            _uiState.value = _uiState.value.copy(
                                movieDetails = details,
                                isLoadingDetails = false,
                                error = null,
                                currentScreen = Screen.DETAILS
                            )
                        } else {
                            _uiState.value = _uiState.value.copy(
                                isLoadingDetails = false,
                                error = "Failed to fetch movie details: ${response.code()}"
                            )
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<MovieDetails>, t: Throwable) {
                        _uiState.value = _uiState.value.copy(
                            isLoadingDetails = false,
                            error = "Network error: ${t.message}"
                        )
                    }
                })
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoadingDetails = false,
                    error = "Error: ${e.message}"
                )
            }
        }
    }

    fun goBackToList() {
        _uiState.value = _uiState.value.copy(
            movieDetails = null,
            currentScreen = Screen.LIST
        )
    }

    fun goBackToSearch() {
        _uiState.value = _uiState.value.copy(
            movies = emptyList(),
            movieDetails = null,
            currentScreen = Screen.SEARCH
        )
    }
}
