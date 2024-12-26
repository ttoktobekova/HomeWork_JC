package com.example.homework_jc.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_jc.data.model.CharacterResponse
import com.example.homework_jc.data.repository.FavoriteCharacterRepository
import com.example.homework_jc.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val repository: Repository,
    private val favoriteCharacterRepository: FavoriteCharacterRepository
) : ViewModel() {

    private val _characters = MutableStateFlow<List<CharacterResponse.Character>>(emptyList())
    val characters: StateFlow<List<CharacterResponse.Character>> get() = _characters

    private val _favoriteCharacters = MutableStateFlow<List<CharacterResponse.Character>>(emptyList())
    val favoriteCharacters: StateFlow<List<CharacterResponse.Character>> get() = _favoriteCharacters

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private var currentPage = 1

    init {
        fetchCharacters()
        loadFavoriteCharacters()
    }

    fun fetchNextPage() {
        if (_isLoading.value) return
        _isLoading.value = true
        currentPage++
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            val response = repository.getCharacters(currentPage)
            if (response.isSuccessful) {
                _characters.value += (response.body()?.results ?: emptyList())
            }
            _isLoading.value = false
        }
    }

    private fun loadFavoriteCharacters() {
        viewModelScope.launch {
            favoriteCharacterRepository.getFavoriteCharacters().collect { favorites ->
                _favoriteCharacters.value = favorites.map { it.toCharacterResponse() }
            }
        }
    }

    fun toggleFavorite(character: CharacterResponse.Character) {
        viewModelScope.launch {
            val favoriteCharacter = character.toFavoriteCharacter()
            if (_favoriteCharacters.value.any { it.id == character.id }) {
                favoriteCharacterRepository.removeFavorite(favoriteCharacter.id)
            } else {
                favoriteCharacterRepository.addFavorite(favoriteCharacter)
            }
            loadFavoriteCharacters()
        }
    }
}