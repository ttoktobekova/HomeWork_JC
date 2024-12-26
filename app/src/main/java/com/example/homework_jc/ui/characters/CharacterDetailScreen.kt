package com.example.homework_jc.ui.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterDetailScreen(characterId: Int) {
    val viewModel: CharacterDetailViewModel = koinViewModel()
    val character = viewModel.character.collectAsState()

    LaunchedEffect(characterId) {
        viewModel.fetchCharacterById(characterId)
    }

    character.value?.let { character ->
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Yellow)
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Name: ${character.name}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Species: ${character.species}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Status: ${character.status}", style = MaterialTheme.typography.bodyLarge)
        }
    } ?: run {
        Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)
    }
}