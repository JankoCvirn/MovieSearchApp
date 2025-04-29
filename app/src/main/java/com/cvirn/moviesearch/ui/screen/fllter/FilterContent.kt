package com.cvirn.moviesearch.ui.screen.fllter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cvirn.moviesearch.viewmodel.SharedViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun FilterContent(sharedViewModel: SharedViewModel) {
    val uiState by sharedViewModel.filterUiState.collectAsStateWithLifecycle()

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(horizontal = 64.dp, vertical = 32.dp),
    ) {
        when (uiState) {
            is FilterUiState.Idle -> {}
            is FilterUiState.Data -> {
                val genreList = (uiState as FilterUiState.Data).genreList
                val selectedGenre = (uiState as FilterUiState.Data).selectedGenre
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(genreList) { genre ->
                        Card(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        8.dp,
                                    ).border(1.dp, Color.LightGray)
                                    .clickable {
                                        sharedViewModel.doFilterAction(
                                            FilterUiAction.SelectedGenre(
                                                genre,
                                            ),
                                        )
                                    },
                        ) {
                            Row {
                                Text(
                                    text = genre.name,
                                    modifier =
                                        Modifier
                                            .background(
                                                color =
                                                    if (
                                                        selectedGenre.id == genre.id
                                                    ) {
                                                        Color.Magenta
                                                    } else {
                                                        Color.Transparent
                                                    },
                                            ).padding(8.dp)
                                            .fillMaxWidth(),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
