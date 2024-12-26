package com.example.homework_jc.data.repository

import com.example.homework_jc.data.model.CharacterResponse
import com.example.homework_jc.data.model.LocationResponse
import retrofit2.Response

class Repository(private val apiService: ApiService) {
    suspend fun getCharacterById(id: Int) = apiService.getCharacterById(id)
    suspend fun getLocationById(id: Int) = apiService.getLocationById(id)
    suspend fun getCharacters(page: Int): Response<CharacterResponse> {
        return apiService.getCharacters(page)
    }

    suspend fun getLocations(): Response<LocationResponse> {

        return apiService.getLocations()
    }
}