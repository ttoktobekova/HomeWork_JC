package com.example.homework_jc.di

import com.example.homework_jc.data.local.AppDatabase
import com.example.homework_jc.data.api.ApiService
import com.example.homework_jc.data.repository.EpisodesRepository
import com.example.homework_jc.data.repository.EpisodesRepositoryImpl
import com.example.homework_jc.data.repository.FavoriteCharacterRepository
import com.example.homework_jc.data.repository.Repository
import com.example.homework_jc.ui.characters.details.CharacterDetailViewModel
import com.example.homework_jc.ui.characters.CharactersViewModel
import com.example.homework_jc.ui.episodes.EpisodesViewModel
import com.example.homework_jc.ui.favorites.FavoriteCharacterViewModel
import com.example.homework_jc.ui.locations.LocationDetailViewModel
import com.example.homework_jc.ui.locations.LocationsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {
    val module = module {
        single {
            Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

        single { AppDatabase.getDatabase(androidContext()) }
        single { get<AppDatabase>().favoriteCharacterDao() }
        single { FavoriteCharacterRepository(get()) }
        single<EpisodesRepository> { EpisodesRepositoryImpl(get()) }
        single { Repository(get()) }

        viewModel { FavoriteCharacterViewModel(get()) }
        viewModel { CharactersViewModel(get(), get()) }
        viewModel { LocationsViewModel(get()) }
        viewModel { CharacterDetailViewModel(get()) }
        viewModel { LocationDetailViewModel(get()) }
        viewModel { EpisodesViewModel(get()) }
    }
}