package com.cvirn.moviesearch.ui.screen.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cvirn.domain.movie.Movie
import com.cvirn.moviesearch.R
import com.cvirn.moviesearch.ui.theme.MovieSearchTheme
import com.cvirn.moviesearch.utils.toCompactCurrency

@Suppress("ktlint:standard:function-naming")
@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .padding(
                    4.dp,
                ).semantics { },
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(2.dp),
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w154/" + movie.image,
                contentDescription = stringResource(R.string.content_description),
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .size(154.dp)
                        .padding(4.dp),
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = stringResource(R.string.rating, movie.rating),
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = stringResource(R.string.budget, movie.budget.toCompactCurrency()),
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = stringResource(R.string.revenue, movie.revenue.toCompactCurrency()),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showSystemUi = true,
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showSystemUi = true,
)
@Composable
fun PreviewSearchItem() {
    MovieSearchTheme {
        MovieItem(
            movie =
                Movie(
                    image = null,
                    title = "Title",
                    budget = 100000,
                    rating = 4.5,
                    revenue = 200000,
                ),
        )
    }
}
