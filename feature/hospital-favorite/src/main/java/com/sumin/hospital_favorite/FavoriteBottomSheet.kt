package com.sumin.hospital_favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sumin.hospital_favorite.databinding.FragmentFavoriteBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val ARG_HOSPITAL_ID = "hospitalId"

@AndroidEntryPoint
class FavoriteBottomSheet : BottomSheetDialogFragment() {

    private val favoriteBottomSheetViewModel by viewModels<FavoriteBottomSheetViewModel>()

    private var _binding: FragmentFavoriteBottomSheetBinding? = null
    private val binding: FragmentFavoriteBottomSheetBinding get() = requireNotNull(_binding)

    private val folderAdapter = FolderAdapter(
        onSelectAddFolder = { FolderAdderDialog().show(requireActivity().supportFragmentManager, "${FolderAdderDialog::class.simpleName}") },
        onSelectFolder = { position, isChecked ->  favoriteBottomSheetViewModel.selectFolder(position, isChecked) }
    )

    private var hospitalId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(DialogFragment.STYLE_NORMAL, R.style.RoundCornerBottomSheetDialogTheme)
        arguments?.let {
            hospitalId = it.getString(ARG_HOSPITAL_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBottomSheetBinding.inflate(inflater)
        binding.viewmodel = favoriteBottomSheetViewModel
        binding.lifecycleOwner = this@FavoriteBottomSheet

        try {
            favoriteBottomSheetViewModel.loadFavoriteFolderList(hospitalId!!)

        } catch (e: Exception) {
            Toast.makeText(requireContext(), "잠시 후에 다시 시도해주세요.", Toast.LENGTH_SHORT)
            dismiss()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvHospitalId.text = hospitalId

            btnClose.setOnClickListener {
                this@FavoriteBottomSheet.dismiss()
            }

            rvFavoriteFolderList.apply {
                adapter = folderAdapter

                val linearLayoutManager = LinearLayoutManager(context)
                layoutManager = linearLayoutManager

                val itemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
                addItemDecoration(itemDecoration)
            }

            btnOk.setOnClickListener {
                favoriteBottomSheetViewModel.submitFavoriteResult()
            }
        }

        viewLifecycleOwner.lifecycleScope.apply {
            launch {
                favoriteBottomSheetViewModel.folderListUiState.collectLatest {
                    if(it.isFetching) {

                    } else {
                        folderAdapter.submitData(it.list)
                    }
                }
            }
        }
    }
}