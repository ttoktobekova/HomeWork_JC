package com.example.homework_jc.ui.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_jc.data.model.LocationResponse
import com.example.homework_jc.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationsViewModel(private val repository: Repository) : ViewModel() {

    private val _locations = MutableStateFlow<List<LocationResponse.Location>>(emptyList())
    val locations: StateFlow<List<LocationResponse.Location>> get() = _locations

    init {
        fetchLocations()
    }

    private fun fetchLocations() {
        viewModelScope.launch {
            val response = repository.getLocations()
            if (response.isSuccessful) {
                _locations.value = response.body()?.results ?: emptyList()
            }
        }
    }
}