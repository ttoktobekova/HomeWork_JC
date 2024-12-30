package com.example.homework_jc.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homework_jc.data.model.LocationResponse
import com.example.homework_jc.data.repository.Repository


class LocationsPagingSource(private val repository: Repository) : PagingSource<Int, LocationResponse.Location>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationResponse.Location> {
        val page = params.key ?: 1
        return try {
            val response = repository.getLocations(page)

            if (response.isSuccessful) {
                val locations = response.body()?.results ?: emptyList()
                Log.d("LocationsPagingSource", "Loaded ${locations.size} locations on page $page")
                LoadResult.Page(
                    data = locations,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (locations.isEmpty()) null else page + 1
                )
            } else {
                Log.e("LocationsPagingSource", "API Error: ${response.code()} ${response.message()}")
                LoadResult.Error(Exception("API Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Log.e("LocationsPagingSource", "Exception: ${e.message}")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LocationResponse.Location>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}