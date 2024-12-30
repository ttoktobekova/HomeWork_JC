package com.example.homework_jc.data.repository

import android.util.Log
import com.example.homework_jc.data.api.ApiService
import com.example.homework_jc.data.model.Episode

class EpisodesRepositoryImpl(private val apiService: ApiService) : EpisodesRepository {
    private var currentPage = 1

    override suspend fun getNextPageEpisodes(): List<Episode> {
        return try {
            val response = apiService.getEpisodes(currentPage)

            if (response.isSuccessful) {
                currentPage++
                response.body()?.results ?: emptyList()
            } else {
                throw Exception("Failed to fetch episodes, response code: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("EpisodesRepositoryImpl", "Error fetching episodes", e)
            throw Exception("Failed to fetch episodes due to network or server issue", e)
        }
    }
}