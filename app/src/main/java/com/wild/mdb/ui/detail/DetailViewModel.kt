package com.wild.mdb.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wild.mdb.data.model.DetailMovie
import com.wild.mdb.data.repository.ServerRepository
import com.wild.mdb.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(private var serverRepository: ServerRepository): ViewModel() {
    private val _uiState = MutableStateFlow<UiState<DetailMovie>>(UiState.Loading)
    val uiState: StateFlow<UiState<DetailMovie>> = _uiState

//    init {
//        fetchDetailMovie(968051)
//    }

    fun fetchDetailMovie(id: Int) {
        viewModelScope.launch {
            serverRepository.getDetailMovie(id)
                .catch {e->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}