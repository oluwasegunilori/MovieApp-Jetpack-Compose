package com.shegz.movieapp.screens.details

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.shegz.movieapp.model.Movie
import com.shegz.movieapp.model.getMovies
import com.shegz.movieapp.widgets.MovieRow


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DetailsScreen(navController: NavController, id: String?) {
    val movie = getMovies().first {
        it.id == id
    }

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.White,
            elevation = 5.dp
        ) {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(100.dp))
                Text(text = "Movie Details", style = MaterialTheme.typography.h5)
            }

        }
    }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                MovieRow(movie = movie)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Movie Images", textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalImageScrollView(movie)

            }

        }
    }

}

@Composable
private fun HorizontalImageScrollView(movie: Movie) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 5.dp
            ) {
                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "Movie Poster"
                )

            }
        }
    }
}

