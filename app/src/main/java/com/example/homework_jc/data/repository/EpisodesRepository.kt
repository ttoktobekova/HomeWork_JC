package com.example.homework_jc.data.repository

import com.example.homework_jc.data.model.Episode

interface EpisodesRepository {
    suspend fun getNextPageEpisodes(): List<Episode>
}