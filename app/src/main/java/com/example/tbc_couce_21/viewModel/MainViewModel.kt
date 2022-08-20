package com.example.tbc_couce_21.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tbc_couce_21.models.Data
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val repository:com.example.tbc_couce_21.paging.PagingData) : ViewModel() {



    fun getMovieList(): Flow<PagingData<Data>> {
        return repository.getAllPersons().cachedIn(viewModelScope)
    }
}