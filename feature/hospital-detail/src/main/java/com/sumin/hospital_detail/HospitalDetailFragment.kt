package com.sumin.hospital_detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sumin.hospital_detail.databinding.FragmentHospitalDetailBinding
import com.sumin.navigation.NavigatorMediator
import com.sumin.navigation.Route
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val ARG_HOSPITAL_ID = "hospitalId"

@AndroidEntryPoint
class HospitalDetailFragment : Fragment() {
    private var hospitalId: String? = null

    private var _binding: FragmentHospitalDetailBinding? = null
    private val binding: FragmentHospitalDetailBinding get() = requireNotNull(_binding)

    private val viewModel : HospitalDetailFragmentViewModel by viewModels()

    @Inject
    lateinit var navigatorMediator: NavigatorMediator

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
        initView()
    }

    private fun initView() {
        binding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            bottomBtnContainer.btnCall.setOnClickListener {
                val telNo = viewModel.uiState.value.telNo
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$telNo"))
                startActivity(intent)
            }
            bottomBtnContainer.btnHomepage.setOnClickListener {
                val url = viewModel.uiState.value.homepageUrl
                navigatorMediator.navigate(Route.ActionHospitalDetailFragmentToWebViewActivity(url))
            }
            btnFavorite.setOnClickListener {
                val id = viewModel.uiState.value.id
                navigatorMediator.navigate(Route.ActionHospitalDetailFragmentToFavoriteBottomSheetDialog(id))
            }
        }
    }
}