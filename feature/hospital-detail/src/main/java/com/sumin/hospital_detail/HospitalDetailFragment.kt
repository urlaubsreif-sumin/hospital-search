package com.sumin.hospital_detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sumin.hospital_detail.databinding.FragmentHospitalDetailBinding
import com.sumin.list.hospital.HospitalModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val ARG_HOSPITAL = "hospital"
private const val ARG_HOSPITAL_ID = "hospitalId"

class HospitalDetailFragment : Fragment() {
    private var hospitalId: String? = null

    private var _binding: FragmentHospitalDetailBinding? = null
    private val binding: FragmentHospitalDetailBinding get() = requireNotNull(_binding)

    private val viewModel by viewModels<HospitalDetailFragmentViewModel> {
        provideHospitalDetailFragmentViewModel(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hospitalId = it.getString(ARG_HOSPITAL_ID)
        }

        viewModel.loadHospitalDetail(hospitalId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_hospital_detail, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this@HospitalDetailFragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}