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

const val ARG_HOSPITAL_ID = "hospitalId"

@AndroidEntryPoint
class FavoriteBottomSheet : BottomSheetDialogFragment() {

    private val favoriteBottomSheetViewModel by viewModels<FavoriteBottomSheetViewModel>()

    private var _binding: FragmentFavoriteBottomSheetBinding? = null
    private val binding: FragmentFavoriteBottomSheetBinding get() = requireNotNull(_binding)

    private val folderAdapter = FolderAdapter(
        onSelectAddFolder = { FolderAdderDialog().show(requireActivity().supportFragmentManager, "${FolderAdderDialog::class.simpleName}") },
        onSelectFolder = { folderId, isChecked ->  favoriteBottomSheetViewModel.selectFolder(folderId, isChecked) }
    )

    private var onSubmitListener: OnSubmitListener? = null

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
            Toast.makeText(requireContext(), "잠시 후에 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvHospitalId.text = hospitalId

            rvFavoriteFolderList.apply {
                adapter = folderAdapter

                val linearLayoutManager = LinearLayoutManager(context)
                layoutManager = linearLayoutManager

                val itemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
                addItemDecoration(itemDecoration)
            }

            btnClose.setOnClickListener {
                this@FavoriteBottomSheet.dismiss()
            }

            try {
                btnOk.setOnClickListener {
                    lifecycleScope.launch {
                        val isFavorite = favoriteBottomSheetViewModel.submitFavoriteResult(hospitalId!!)
                        onSubmitListener?.onSubmit(hospitalId!!, isFavorite)
                        dismiss()
                    }
                }

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "잠시 후에 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                dismiss()
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

    fun setListener(listener: OnSubmitListener) {
        this.onSubmitListener = listener
    }

    interface OnSubmitListener {
        fun onSubmit(hospitalId: String, isFavorite: Boolean)
    }
}