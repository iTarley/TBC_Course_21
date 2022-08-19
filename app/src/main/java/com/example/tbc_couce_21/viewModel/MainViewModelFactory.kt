package com.example.tbc_couce_21.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tbc_couce_21.network.RetrofitClient
import com.example.tbc_couce_21.paging.PagingData

class MainViewModelFactory(private val response:PagingData):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.response) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}