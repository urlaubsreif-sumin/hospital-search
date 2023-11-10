package com.sumin.hospital_favorite

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.sumin.hospital_favorite.databinding.DialogFolderAdderBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FolderAdderDialog : DialogFragment() {

    private val favoriteBottomSheetViewModel by viewModels<FavoriteBottomSheetViewModel>()

    private var _binding: DialogFolderAdderBinding? = null
    private val binding: DialogFolderAdderBinding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFolderAdderBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.lifecycleOwner = this
        binding.viewmodel = favoriteBottomSheetViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnClose.setOnClickListener { dismiss() }
            btnOk.setOnClickListener {
                val result = submitFolderName()
                Log.i("[search test]", "btnOk clicked? -> $result")
                if(result) {
                    dismiss()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val windowManager = activity?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val size = windowManager.currentWindowMetricsPointCompat()
        val deviceWidth = size.x

        params?.width = (deviceWidth * 0.8).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    private fun submitFolderName(): Boolean {
        binding.apply {
            return if(favoriteBottomSheetViewModel.folderAdderDialogUiState.value.getMessage().isEmpty()) {
                val folderName = etFolderName.text.toString()
                favoriteBottomSheetViewModel.addFolder(folderName)
                true

            } else {
                val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.shake)
                tvWarning.startAnimation(animation)
                tvWarning.requestFocus()
                false
            }
        }
    }
}