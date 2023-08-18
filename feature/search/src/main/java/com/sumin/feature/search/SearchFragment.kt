package com.sumin.feature.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumin.feature.search.databinding.FragmentSearchBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {

    private val searchFragmentViewModel by viewModels<SearchFragmentViewModel> {
        provideSearchFragmentViewModel()
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = requireNotNull(_binding)

    private val hospitalAdapter = HospitalAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchFragmentViewModel.queryResult.collectLatest {
                    hospitalAdapter.submitData(it)
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
        initView()

        viewLifecycleOwner.lifecycleScope.launch {
            hospitalAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
            }
        }
    }

    private fun initView() {
        binding.apply {

            rvHospitalList.apply {
                adapter = hospitalAdapter

                val linearLayoutManager = LinearLayoutManager(context)
                layoutManager = linearLayoutManager

                val itemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
                addItemDecoration(itemDecoration)
            }

            searchView.apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        searchFragmentViewModel.onHospitalNameQueryChanged(newText ?: "")
                        return true
                    }
                })

                isSubmitButtonEnabled = true
            }
        }
    }
}