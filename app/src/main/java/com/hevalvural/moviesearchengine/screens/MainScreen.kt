package com.hevalvural.moviesearchengine.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp



@Composable
fun MainScreen(searchMovie : (search: String) -> Unit){

    val search = remember { mutableStateOf("") }

    Box(modifier = Modifier.
    background(Color(0XFF222831))) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp, bottom = 200.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = search.value,
                onValueChange = { search.value = it },
                placeholder = { Text("What do you want to watch today?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF393E46),
                    unfocusedContainerColor = Color(0xFF393E46),
                    focusedIndicatorColor = Color(0xFFFFD369),
                    unfocusedIndicatorColor = Color(0xFF666666),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedPlaceholderColor = Color(0xFFAAAAAA),
                    unfocusedPlaceholderColor = Color(0xFFAAAAAA)
                )
            )
            Spacer(modifier = Modifier.padding(bottom = 50.dp))
            Button(
                onClick = {searchMovie(search.value)},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFD369),
                    contentColor = Color(0xFF393E46)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Search",
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}




