package com.example.homework_jc.ui.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_jc.data.model.Episode
import com.example.homework_jc.data.repository.EpisodesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class EpisodesViewModel(private val repository: EpisodesRepository) : ViewModel() {

    private val _episodes = MutableStateFlow<List<Episode>>(emptyList())
    val episodes: StateFlow<List<Episode>> = _episodes
    private val _loading = MutableStateFlow(false)

    init {
        loadMoreEpisodes()
    }

    fun loadMoreEpisodes() {
        viewModelScope.launch {
            _loading.value = true

            try {
                val newEpisodes = repository.getNextPageEpisodes()
                _episodes.value += newEpisodes
            } catch (e: Exception) {
            } finally {
                _loading.value = false
            }
        }
    }
}