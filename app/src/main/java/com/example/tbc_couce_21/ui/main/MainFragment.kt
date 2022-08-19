package com.example.tbc_couce_21.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_couce_21.databinding.FragmentMainBinding
import com.example.tbc_couce_21.network.RetrofitClient
import com.example.tbc_couce_21.paging.PagerAdapter
import com.example.tbc_couce_21.paging.PagingData
import com.example.tbc_couce_21.viewModel.MainViewModel
import com.example.tbc_couce_21.viewModel.MainViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = PagerAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val service = RetrofitClient.invoke()
        val rep = PagingData(service)
        val factory = MainViewModelFactory(rep)

        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        binding.recyclerview.adapter = adapter
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading)
                binding.progressDialog.isVisible = true
            else {
                binding.progressDialog.isVisible = false
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(context, it.error.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }

        lifecycleScope.launch {
            viewModel.getMovieList().collectLatest {
                adapter.submitData(it)
            }
        }



    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

