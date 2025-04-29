package com.cvirn.moviesearch.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cvirn.moviesearch.R
import com.cvirn.moviesearch.viewmodel.SharedViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeContent(
    sharedViewModel: SharedViewModel,
    goFilterContent: () -> Unit,
) {
    val uiState by sharedViewModel.homeUiState.collectAsStateWithLifecycle()

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 8.dp),
    ) {
        when (uiState) {
            is HomeUiState.Idle -> {}
            is HomeUiState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = stringResource(R.string.error))
                }
            }

            is HomeUiState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator(
                        modifier =
                            Modifier
                                .width(64.dp),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }

            is HomeUiState.Data -> {
                val movieList = (uiState as HomeUiState.Data).movieList
                LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                    items(movieList) { movie ->
                        MovieItem(movie = movie)
                    }
                }
                FloatingActionButton(
                    onClick = goFilterContent,
                    modifier =
                        Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                            .size(48.dp),
                    shape = MaterialTheme.shapes.extraLarge,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(48.dp),
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "contentDescription",
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }
        }
    }
}
