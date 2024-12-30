package com.example.homework_jc.ui.characters.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_jc.data.model.CharacterResponse
import com.example.homework_jc.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val repository: Repository) : ViewModel() {

    private val _character = MutableStateFlow<CharacterResponse.Character?>(null)
    val character: StateFlow<CharacterResponse.Character?> get() = _character

    fun fetchCharacterById(id: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(id)
            if (response.isSuccessful) {
                _character.value = response.body()
            }
        }
    }
}