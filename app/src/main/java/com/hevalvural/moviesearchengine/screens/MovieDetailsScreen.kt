package com.hevalvural.moviesearchengine.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hevalvural.moviesearchengine.models.MovieDetails

@Composable
fun MovieDetailsScreen(
    movieDetails: MovieDetails?,
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
                        text = "Loading movie details...",
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
            movieDetails == null -> {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No movie details found",
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
                    // Header with back button
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
                    
                    // Movie details content
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    ) {
                        // Poster and basic info
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            AsyncImage(
                                model = movieDetails.poster,
                                contentDescription = "Movie Poster",
                                modifier = Modifier
                                    .size(150.dp, 200.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                            
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = movieDetails.title,
                                    color = Color.White,
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                
                                Text(
                                    text = "Year: ${movieDetails.year}",
                                    color = Color(0xFFFFD369),
                                    style = MaterialTheme.typography.titleMedium
                                )
                                
                                Text(
                                    text = "Rated: ${movieDetails.rated}",
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                
                                Text(
                                    text = "Runtime: ${movieDetails.runtime}",
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                
                                Text(
                                    text = "IMDb Rating: ${movieDetails.imdbRating}",
                                    color = Color(0xFFFFD369),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        // Plot
                        Text(
                            text = "Plot",
                            color = Color(0xFFFFD369),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = movieDetails.plot,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 1.5
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        // Director
                        Text(
                            text = "Director",
                            color = Color(0xFFFFD369),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = movieDetails.director,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        // Cast
                        Text(
                            text = "Cast",
                            color = Color(0xFFFFD369),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = movieDetails.actors,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        // Genre
                        Text(
                            text = "Genre",
                            color = Color(0xFFFFD369),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = movieDetails.genre,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        // Additional details
                        Text(
                            text = "Additional Details",
                            color = Color(0xFFFFD369),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        if (movieDetails.awards.isNotEmpty() && movieDetails.awards != "N/A") {
                            Text(
                                text = "Awards: ${movieDetails.awards}",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        
                        if (movieDetails.country.isNotEmpty() && movieDetails.country != "N/A") {
                            Text(
                                text = "Country: ${movieDetails.country}",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        
                        if (movieDetails.language.isNotEmpty() && movieDetails.language != "N/A") {
                            Text(
                                text = "Language: ${movieDetails.language}",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
