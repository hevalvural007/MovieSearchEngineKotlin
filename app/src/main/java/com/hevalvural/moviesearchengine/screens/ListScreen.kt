package com.hevalvural.moviesearchengine.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hevalvural.moviesearchengine.MovieRow
import com.hevalvural.moviesearchengine.models.Movie

@Composable
fun ListScreen(
    movies: List<Movie>,
    isLoading: Boolean,
    error: String?,
    onBackPressed: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(Color(0XFF222831))) {
        when {
            isLoading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFFFFD369))
                    Text(
                        text = "Searching for movies...",
                        color = Color.White,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
            error != null -> {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Error: $error",
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = onBackPressed,
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Go Back")
                    }
                }
            }
            movies.isEmpty() -> {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No movies found",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = onBackPressed,
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Go Back")
                    }
                }
            }
            else -> {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF393E46))
                            .padding(16.dp)
                    ) {
                        Button(
                            onClick = onBackPressed,
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Text("Back")
                        }
                    }
                    
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(movies) { movie ->
                            MovieRow(movie)
                            Box(modifier = Modifier.fillMaxWidth().height(5.dp).background(Color(0xFFF4CE14)))
                        }
                    }
                }
            }
        }
    }
}
