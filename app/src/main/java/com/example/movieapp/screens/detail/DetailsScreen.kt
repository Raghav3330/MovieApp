package com.example.movieapp.screens.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.widgets.MovieRow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movieId: String?){
    val newMovieList = getMovies().filter { movie -> movie.id == movieId }
    Scaffold(topBar = { TopAppBar(colors = TopAppBarDefaults.mediumTopAppBarColors(Color.LightGray),
        title = {
            Row(horizontalArrangement = Arrangement.Start) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back",
                modifier = Modifier.clickable { navController.popBackStack() })
                Spacer(modifier = Modifier.width(70.dp))
                Text(text = "Movies")
            }
        })
    }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                MovieRow(newMovieList.first())
                Spacer(modifier = Modifier.height(8.dp))
                Divider()

                Text(text = "Movie Images")
                HorizontalScrollableImageView(newMovieList)
            }
        }
    }
}

@Composable
private fun HorizontalScrollableImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList[0].images) { image ->
            Card(modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp), elevation = CardDefaults.cardElevation(5.dp) ) {
                Image(
                    painter = rememberAsyncImagePainter(image),
                    contentDescription = "Movie Poster"
                )
            }
        }
    }
}