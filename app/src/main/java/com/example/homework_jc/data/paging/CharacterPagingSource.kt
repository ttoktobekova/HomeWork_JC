package com.example.homework_jc.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homework_jc.data.model.CharacterResponse
import com.example.homework_jc.data.repository.Repository


class CharacterPagingSource(private val repository: Repository) :
    PagingSource<Int, CharacterResponse.Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse.Character> {
        val page = params.key ?: 1
        return try {
            val response = repository.getCharacters(page)

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse.Character>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey }
    }
}