package com.example.tbc_couce_21.paging

import androidx.paging.*
import androidx.paging.PagingData
import com.example.tbc_couce_21.models.Data
import com.example.tbc_couce_21.network.RetrofitClient
import kotlinx.coroutines.flow.Flow

class PagingData(private val retrofit: RetrofitClient) {


    fun getAllPersons(): Flow<PagingData<Data>> {

        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                PagingSource(retrofit)
            }
        , initialKey = 1
        ).flow

    }


}