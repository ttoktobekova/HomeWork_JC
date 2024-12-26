package com.example.homework_jc.data.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homework_jc.ui.characters.CharacterDetailScreen
import com.example.homework_jc.ui.characters.CharactersScreen
import com.example.homework_jc.ui.common.AppBottomBar
import com.example.homework_jc.ui.common.AppTopBar
import com.example.homework_jc.ui.episodes.EpisodesScreen
import com.example.homework_jc.ui.episodes.EpisodesViewModel
import com.example.homework_jc.ui.favorites.FavoriteCharacterViewModel
import com.example.homework_jc.ui.favorites.FavoriteCharactersScreen
import com.example.homework_jc.ui.locations.LocationDetailScreen
import com.example.homework_jc.ui.locations.LocationsScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val episodesViewModel = koinViewModel<EpisodesViewModel>()

    Scaffold(
        topBar = { AppTopBar(navController) },
        bottomBar = { AppBottomBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navController, startDestination = "characters") {

                composable("characters") { CharactersScreen(navController) }
                composable("locations") { LocationsScreen(navController) }
                composable("episodes") {
                    EpisodesScreen(episodesViewModel)
                }
                composable("character_detail/{characterId}") { backStackEntry ->
                    val characterId =
                        backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
                    if (characterId != null) {
                        CharacterDetailScreen(characterId)
                    }
                }
                composable("location_detail/{locationId}") { backStackEntry ->
                    val locationId =
                        backStackEntry.arguments?.getString("locationId")?.toIntOrNull()
                    if (locationId != null) {
                        LocationDetailScreen(locationId)
                    }
                }
                composable("favorites") {
                    val viewModel: FavoriteCharacterViewModel = koinViewModel()
                    FavoriteCharactersScreen(viewModel = viewModel)
                }
            }
        }
    }
}