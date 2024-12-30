package com.example.homework_jc.data.nav

import CharactersScreen
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homework_jc.data.appbar.AppBottomBar
import com.example.homework_jc.data.appbar.AppTopBar
import com.example.homework_jc.ui.anim.CustomScreenAnimations
import com.example.homework_jc.ui.characters.details.CharacterDetailScreen
import com.example.homework_jc.ui.episodes.EpisodesScreen
import com.example.homework_jc.ui.favorites.FavoriteCharacterViewModel
import com.example.homework_jc.ui.favorites.FavoriteCharactersScreen
import com.example.homework_jc.ui.locations.LocationDetailScreen
import com.example.homework_jc.ui.locations.LocationsScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainNavScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { AppTopBar(navController) },
        bottomBar = { AppBottomBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = "characters"
            ) {
                composableWithTransitions("characters") { CharactersScreen(navController) }
                composableWithTransitions("locations") { LocationsScreen(navController) }
                composableWithTransitions("episodes") { EpisodesScreen(navController) }
                composableWithTransitions("character_detail/{characterId}") { backStackEntry ->
                    val characterId =
                        backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
                    if (characterId != null) {
                        CharacterDetailScreen(characterId)
                    }
                }
                composableWithTransitions("location_detail/{locationId}") { backStackEntry ->
                    val locationId =
                        backStackEntry.arguments?.getString("locationId")?.toIntOrNull()
                    if (locationId != null) {
                        LocationDetailScreen(locationId)
                    }
                }
                composableWithTransitions("favorites") {
                    val viewModel: FavoriteCharacterViewModel = koinViewModel()
                    FavoriteCharactersScreen(viewModel = viewModel)
                }
            }
        }
    }
}

fun NavGraphBuilder.composableWithTransitions(
    route: String,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        enterTransition = { CustomScreenAnimations.enterFromRight },
        exitTransition = { CustomScreenAnimations.exitToLeft },
        popEnterTransition = { CustomScreenAnimations.popEnterFromLeft },
        popExitTransition = { CustomScreenAnimations.popExitToRight },
        content = content
    )
}