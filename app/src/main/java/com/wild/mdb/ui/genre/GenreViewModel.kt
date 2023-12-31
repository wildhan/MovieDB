package com.wild.mdb.ui.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wild.mdb.data.model.Genre
import com.wild.mdb.data.repository.ServerRepository
import com.wild.mdb.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class GenreViewModel(private val serverRepository: ServerRepository):ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Genre>>>(UiState.Loading)
    val uiState:StateFlow<UiState<List<Genre>>> = _uiState

    init {
        fetchGenre()
    }

    private fun fetchGenre(){
        viewModelScope.launch {
            serverRepository.getGenres()
                .catch{ e->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}