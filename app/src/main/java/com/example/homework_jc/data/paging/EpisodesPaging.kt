package com.example.homework_jc.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homework_jc.data.model.Episode
import com.example.homework_jc.data.repository.Repository

class EpisodesPaging(
    private val repository: Repository
) : PagingSource<Int, Episode>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        return try {
            val page = params.key ?: 1
            val response = repository.getEpisodes(page)

            if (response.isSuccessful) {
                val episodes = response.body()?.results ?: emptyList()
                LoadResult.Page(
                    data = episodes,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (episodes.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception("Failed to load episodes"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}