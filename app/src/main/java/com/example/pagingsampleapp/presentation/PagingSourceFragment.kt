package com.example.pagingsampleapp.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.example.pagingsampleapp.R
import com.example.pagingsampleapp.databinding.FragmentPagingSourceBinding
import com.example.pagingsampleapp.presentation.adapter.AnimeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@AndroidEntryPoint
class PagingSourceFragment: Fragment(R.layout.fragment_paging_source) {

    private lateinit var binding: FragmentPagingSourceBinding
    private val viewModel: PagingSourceViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPagingSourceBinding.bind(view)

        val adapter = AnimeAdapter()

        lifecycleScope.launch {
            viewModel.getAnimesAsFlow().collectLatest {
                adapter.submitData(it)
            }
        }

        viewModel.getAnimesAsLiveData().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
//                adapter.submitData(it)
            }
        }
        binding.articleRecycler.adapter = adapter
    }
}