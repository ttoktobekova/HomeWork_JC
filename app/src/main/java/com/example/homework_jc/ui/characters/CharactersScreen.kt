import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.homework_jc.ui.characters.CharactersViewModel
import com.example.homework_jc.ui.characters.card.CharacterCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharactersScreen(navController: NavController) {

    val viewModel: CharactersViewModel = koinViewModel()
    val pagingItems = viewModel.characters.collectAsLazyPagingItems()
    val isLoading = pagingItems.loadState.refresh == LoadState.Loading
    val favoriteCharacters = viewModel.favoriteCharacters.collectAsState().value

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(pagingItems.itemCount) { index ->
            val character = pagingItems[index]
            character?.let {
                val isFavorite = favoriteCharacters.any { favorite -> favorite.id == character.id }

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
        }

        when (pagingItems.loadState.append) {
            is LoadState.Loading -> {
                item {
                    CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
                }
            }

            is LoadState.Error -> {
                item {
                    Button(
                        onClick = { pagingItems.retry() },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(text = "Retry")
                    }
                }
            }

            else -> {}
        }

        if (isLoading && pagingItems.itemCount == 0) {
            item {
                CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
            }
        }
    }
}