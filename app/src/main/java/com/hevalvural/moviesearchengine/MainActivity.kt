
package com.hevalvural.moviesearchengine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hevalvural.moviesearchengine.screens.ListScreen
import com.hevalvural.moviesearchengine.screens.MainScreen
import com.hevalvural.moviesearchengine.screens.MovieDetailsScreen
import com.hevalvural.moviesearchengine.ui.theme.MovieSearchEngineTheme
import com.hevalvural.moviesearchengine.viewmodels.MovieViewModel
import com.hevalvural.moviesearchengine.viewmodels.Screen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieSearchEngineTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)){

                        MovieApp()
                    }
                }
            }
        }
    }
}

@Composable
fun MovieApp(viewModel: MovieViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    
    when (uiState.currentScreen) {
        Screen.SEARCH -> {
            MainScreen(
                searchMovie = { query ->
                    viewModel.searchMovies(query)
                }
            )
        }
        Screen.LIST -> {
            ListScreen(
                movies = uiState.movies,
                isLoading = uiState.isLoading,
                error = uiState.error,
                onBackPressed = {
                    viewModel.goBackToSearch()
                },
                onMovieClick = { movie ->
                    viewModel.getMovieDetails(movie.imdbID)
                }
            )
        }
        Screen.DETAILS -> {
            MovieDetailsScreen(
                movieDetails = uiState.movieDetails,
                isLoading = uiState.isLoadingDetails,
                error = uiState.error,
                onBackPressed = {
                    viewModel.goBackToList()
                }
            )
        }
    }
}






@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieSearchEngineTheme {

    }
}