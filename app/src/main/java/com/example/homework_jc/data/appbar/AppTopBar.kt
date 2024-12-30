package com.example.homework_jc.data.appbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navController: NavController) {

    val currentRoute = remember { mutableStateOf<String?>(null) }

    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            currentRoute.value = destination.route
        }
        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    val title = when (currentRoute.value) {
        "characters" -> "Characters"
        "locations" -> "Locations"
        "character_detail/{characterId}" -> "Character Details"
        "location_detail/{locationId}" -> "Location Details"
        "episodes" -> "Episodes"
        "favorites" -> "Favorites"
        else -> "Details"
    }

    TopAppBar(
        title = {
            Text(text = title)
        }
    )
}