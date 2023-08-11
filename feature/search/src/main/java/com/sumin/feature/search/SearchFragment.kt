package com.sumin.feature.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sumin.feature.search.databinding.FragmentSearchBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.math.max


class SearchFragment : Fragment() {

    private val searchFragmentViewModel by viewModels<SearchFragmentViewModel> {
        provideSearchFragmentViewModel()
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = requireNotNull(_binding)

    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchFragmentViewModel.uiState.collect { uiState ->
                    binding.progressBar.isVisible = uiState.isFetchingHospitals
                    binding.result.text =
                        if(uiState.messages.isNotEmpty()) {
                            val msg = uiState.messages.joinToString(",") { it.message }
                            Log.i("[api test]", "msg = $msg")
                            msg

                        } else {
                            uiState.items.map {
                                Log.i("[api test]", "items... ${it.hospitalName}")
                                it.hospitalName
                            }.joinToString(", ")
                        }

                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.apply {
            lifecycleOwner = this@SearchFragment
            viewmodel = searchFragmentViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.prevPage.setOnClickListener { page = max(1, page - 1) }
        binding.nextPage.setOnClickListener { page += 1 }
        binding.search.setOnClickListener { searchFragmentViewModel.searchHospitals(page) }
    }
}