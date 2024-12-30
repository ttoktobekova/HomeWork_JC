package com.example.homework_jc.ui.locations

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.homework_jc.ui.locations.card.LocationCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun LocationsScreen(navController: NavController) {
    val viewModel: LocationsViewModel = koinViewModel()
    val pagingItems = viewModel.locations.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.padding(16.dp)) {

        items(pagingItems.itemCount) { index ->
            val location = pagingItems[index]
            location?.let {
                LocationCard(
                    location = it,
                    onClick = {
                        navController.navigate("location_detail/${it.id}")
                    }
                )
            }
        }

        if (pagingItems.loadState.refresh == LoadState.Loading && pagingItems.itemCount == 0) {
            item {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        }

        if (pagingItems.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        }

        if (pagingItems.loadState.refresh is LoadState.Error || pagingItems.loadState.append is LoadState.Error) {
            item {
                Text(
                    text = "Ошибка загрузки. Попробуйте ещё раз.",
                    modifier = Modifier.padding(16.dp)
                )
                Button(
                    onClick = {
                        pagingItems.retry()
                        Log.e("LocationsScreen", "Error while fetching locations")
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Retry")
                }
            }
        }
    }
}