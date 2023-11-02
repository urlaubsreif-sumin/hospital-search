package com.sumin.hospital_favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sumin.hospital_favorite.databinding.FragmentFavoriteBottomSheetBinding

private const val ARG_HOSPITAL_ID = "hospitalId"

class FavoriteBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentFavoriteBottomSheetBinding? = null
    private val binding: FragmentFavoriteBottomSheetBinding get() = requireNotNull(_binding)

    private var hospitalId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvHospitalId.text = hospitalId
        binding.btnClose.setOnClickListener {
            this@FavoriteBottomSheet.dismiss()
        }
    }
}