package com.example.homework_jc.ui.locations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel


@Composable
fun LocationDetailScreen(locationId: Int) {
    val viewModel: LocationDetailViewModel = koinViewModel()
    val location = viewModel.location.collectAsState()

    LaunchedEffect(locationId) {
        viewModel.fetchLocationById(locationId)
    }

    location.value?.let { location ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${location.name}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Type: ${location.type}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Dimension: ${location.dimension}", style = MaterialTheme.typography.bodyLarge)
        }
    } ?: run {
        Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)
    }
}