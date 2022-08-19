package com.example.tbc_couce_21.viewModel

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tbc_couce_21.models.Data
import com.example.tbc_couce_21.network.RetrofitClient
import com.example.tbc_couce_21.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(private val repository:com.example.tbc_couce_21.paging.PagingData) : ViewModel() {


    val errorMessage = MutableStateFlow<String>("Error")

    fun getMovieList(): Flow<PagingData<Data>> {
        return repository.getAllPersons().cachedIn(viewModelScope)
    }
}