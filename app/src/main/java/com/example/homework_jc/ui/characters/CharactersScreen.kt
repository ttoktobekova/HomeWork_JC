package com.example.homework_jc.ui.characters

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homework_jc.ui.common.CharacterCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharactersScreen(navController: NavController) {
    val viewModel: CharactersViewModel = koinViewModel()
    val characters = viewModel.characters.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val favoriteCharacters = viewModel.favoriteCharacters.collectAsState().value

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(characters) { character ->
            val isFavorite = favoriteCharacters.contains(character)

            CharacterCard(
                character = character,
                onClick = {
                    navController.navigate("character_detail/${character.id}")
                },
                isFavorite = isFavorite,
                onFavoriteClick = {
                    viewModel.toggleFavorite(character)
                }
            )
        }

        if (!isLoading) {
            item {
                Button(
                    onClick = { viewModel.fetchNextPage() },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Load More")
                }
            }
        }

        if (isLoading && characters.isNotEmpty()) {
            item {
                CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
            }
        }
    }

    LaunchedEffect(key1 = characters.size) {
        if (characters.size >= 20 && !isLoading) {
            viewModel.fetchNextPage()
        }
    }
}