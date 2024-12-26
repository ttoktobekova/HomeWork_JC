package com.example.homework_jc.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
@Composable
fun AppBottomBar(
    navController: NavController,
) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Characters") },
            label = { Text("Characters") },
            selected = navController.currentBackStackEntry?.destination?.route == "characters",
            onClick = {
                navController.navigate("characters") {
                    popUpTo("characters") { inclusive = true }
                    launchSingleTop = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.LocationOn, contentDescription = "Locations") },
            label = { Text("Locations") },
            selected = navController.currentBackStackEntry?.destination?.route == "locations",
            onClick = {
                navController.navigate("locations") {
                    popUpTo("locations") { inclusive = true }
                    launchSingleTop = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Episodes") },
            label = { Text("Episodes") },
            selected = navController.currentBackStackEntry?.destination?.route == "episodes",
            onClick = {
                if (navController.currentBackStackEntry?.destination?.route != "episodes") {
                    navController.navigate("episodes") {
                        popUpTo("episodes") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Star, contentDescription = "Favorites") },
            label = { Text("Favorites") },
            selected = navController.currentBackStackEntry?.destination?.route == "favorites",
            onClick = {
                navController.navigate("favorites") {
                    launchSingleTop = true
                }
            }
        )
    }
}