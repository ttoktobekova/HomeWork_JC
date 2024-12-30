package com.example.homework_jc.ui.episodes

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.homework_jc.ui.episodes.card.EpisodeCard
import org.koin.androidx.compose.koinViewModel


@Composable
fun EpisodesScreen(navController: NavController, episodesViewModel: EpisodesViewModel = koinViewModel()) {
    val pagingItems = episodesViewModel.episodes.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(pagingItems.itemCount) { index ->
            val episode = pagingItems[index]
            episode?.let {
                EpisodeCard(
                    episode = it,
                    onClick = {
                        navController.navigate("episode_detail/${it.id}")
                    }
                )
            }
        }

        if (pagingItems.loadState.append == LoadState.Loading) {
            item { CircularProgressIndicator(modifier = Modifier.padding(16.dp)) }
        }

        if (pagingItems.loadState.append is LoadState.Error || pagingItems.loadState.refresh is LoadState.Error) {
            item { Text("Error loading episodes. Please try again.") }
        }

        if (pagingItems.loadState.refresh == LoadState.Loading && pagingItems.itemCount == 0) {
            item { CircularProgressIndicator(modifier = Modifier.padding(16.dp)) }
        }
    }
}