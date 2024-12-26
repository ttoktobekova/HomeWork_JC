package com.example.homework_jc.ui.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_jc.data.local.FavoriteCharacter
import com.example.homework_jc.data.repository.FavoriteCharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteCharacterViewModel(
    private val repository: FavoriteCharacterRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<FavoriteCharacter>>(emptyList())
    val favorites: StateFlow<List<FavoriteCharacter>> = _favorites
    private val _error = MutableStateFlow<Throwable?>(null)

    init {
        observeFavorites()
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            try {
                repository.getFavoriteCharacters()
                    .collect { characters ->
                        _favorites.value = characters
                    }
            } catch (e: Exception) {
                _error.value = e
            }
        }
    }

    fun toggleFavorite(character: FavoriteCharacter) {
        viewModelScope.launch {
            try {
                if (character.isFavorite == 1) {
                    repository.removeFavorite(character.id)
                } else {
                    repository.addFavorite(character.copy(isFavorite = 1))
                }
            } catch (e: Exception) {
                _error.value = e
            }
        }
    }

    fun removeCharacterFromFavorites(character: FavoriteCharacter) {
        viewModelScope.launch {
            try {
                repository.removeFavorite(character.id)
            } catch (e: Exception) {
                _error.value = e
                Log.e("FavoriteCharacterVM", "Error removing favorite character", e)
            }
        }
    }
}