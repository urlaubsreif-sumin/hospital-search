package com.sumin.feature.search

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.sumin.feature.search.databinding.FragmentSearchBinding
import com.sumin.navigation.NavigatorMediator
import com.sumin.navigation.Route
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchFragmentViewModel by viewModels<SearchFragmentViewModel>()

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = requireNotNull(_binding)

    @Inject
    lateinit var navigatorMediator: NavigatorMediator

    private val hospitalAdapter = HospitalAdapter(
        onItemClick = { id ->
            navigatorMediator.navigate(Route.ActionSearchFragmentToHospitalDetailFragment(id))
        },
        onFavoriteClick = { id ->
            navigatorMediator.navigate(Route.ActionSearchFragmentToFavoriteBottomSheetDialog(id))

        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        viewLifecycleOwner.lifecycleScope.apply {
            launch {
                searchFragmentViewModel.queryResult.collectLatest {
                    Log.i("[search test]", "PAGING DATA <--- ")
                    hospitalAdapter.submitData(it)
                }
            }

            launch {
                hospitalAdapter.loadStateFlow.collectLatest { loadStates ->
                    binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
                }
            }
        }
    }

    private fun initView() {
        binding.apply {

            rvHospitalList.apply {
                adapter = hospitalAdapter

//                val linearLayoutManager = LinearLayoutManager(context)
//                layoutManager = linearLayoutManager
//
//                val itemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
                val itemDecoration = GridSpacingItemDecorator(16)
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

            swipeRefreshLayout.setOnRefreshListener {
                hospitalAdapter.refresh()
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    internal class GridSpacingItemDecorator(private val padding: Int) :
        RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = padding
            outRect.bottom = padding
            outRect.left = padding
            outRect.right = padding
        }
    }
}