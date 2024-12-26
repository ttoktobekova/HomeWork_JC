package com.example.homework_jc.ui.locations

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.homework_jc.ui.common.LocationCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun LocationsScreen(navController: NavController) {

    val viewModel: LocationsViewModel = koinViewModel()
    val locations = viewModel.locations.collectAsState()

    LazyColumn {
        items(locations.value) { location ->
            LocationCard(location) {
                navController.navigate("location_detail/${location.id}")
            }
        }
    }
}