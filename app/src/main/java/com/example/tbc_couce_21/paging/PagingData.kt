package com.example.tbc_couce_21.paging

import androidx.lifecycle.LiveData
import androidx.paging.*
import androidx.paging.PagingData
import com.example.tbc_couce_21.models.Data
import com.example.tbc_couce_21.models.InfoModel
import com.example.tbc_couce_21.models.NETWORK_PAGE_SIZE
import com.example.tbc_couce_21.network.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Retrofit

class PagingData(private val retrofit: RetrofitClient) {


    fun getAllPersons(): Flow<PagingData<Data>> {

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                PagingSource(retrofit)
            }
        , initialKey = 1
        ).flow

    }


}