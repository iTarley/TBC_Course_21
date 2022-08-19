package com.example.tbc_couce_21.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tbc_couce_21.models.Data
import com.example.tbc_couce_21.network.RetrofitClient
import java.lang.Exception


class PagingSource(private val retrofitClient: RetrofitClient):PagingSource<Int, Data>() {

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {

        return try{
            val position = params.key ?: 1
            val response = retrofitClient.getInfo(position,6)
            Log.d("response", "load: ${response.body()?.data}")
            LoadResult.Page(data = response.body()!!.data, prevKey = if (position == 1) null else position -1,nextKey = position + 1 )
        }catch (e:Exception){
            LoadResult.Error(e)
        }



    }
}