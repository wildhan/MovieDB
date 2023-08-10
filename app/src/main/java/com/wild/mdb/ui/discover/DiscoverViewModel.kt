package com.wild.mdb.ui.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wild.mdb.data.model.Movie
import com.wild.mdb.data.repository.ServerRepository
import com.wild.mdb.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DiscoverViewModel(private val serverRepository: ServerRepository):ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Movie>>> = _uiState

    init {
        fetchDiscoverMovie()
    }

    private fun fetchDiscoverMovie(){
        viewModelScope.launch {
            serverRepository.getDiscoverMovie(1,28)
                .catch{ e->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}