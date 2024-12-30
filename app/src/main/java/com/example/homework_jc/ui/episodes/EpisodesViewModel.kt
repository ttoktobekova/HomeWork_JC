package com.example.homework_jc.ui.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.homework_jc.data.paging.EpisodesPaging
import com.example.homework_jc.data.repository.Repository


class EpisodesViewModel(
    private val repository: Repository
) : ViewModel() {

    val episodes = Pager(PagingConfig(pageSize = 20)) {
        EpisodesPaging(repository)
    }.flow.cachedIn(viewModelScope)
}