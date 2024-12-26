package com.example.homework_jc.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCharacterDao {
    @Query("SELECT * FROM favorite_characters")
    fun getFavoriteCharacters(): Flow<List<FavoriteCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(character: FavoriteCharacter)

    @Query("DELETE FROM favorite_characters WHERE id = :characterId")
    suspend fun removeFromFavorites(characterId: Int)
}