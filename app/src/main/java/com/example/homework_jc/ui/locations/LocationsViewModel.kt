package com.example.homework_jc.ui.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.homework_jc.data.model.LocationResponse
import com.example.homework_jc.data.paging.LocationsPagingSource
import com.example.homework_jc.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationsViewModel(private val repository: Repository) : ViewModel() {

    val locations = Pager(PagingConfig(pageSize = 20)) {
        LocationsPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}