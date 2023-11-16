package com.sumin.hospital

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sumin.feature.search.SearchFragmentDirections
import com.sumin.hospital_detail.HospitalDetailFragmentDirections
import com.sumin.navigation.Navigator
import com.sumin.navigation.Route
import javax.inject.Inject

class NavigatorImpl @Inject constructor(private val activity: FragmentActivity) : Navigator {

    private val navController: NavController by lazy {
        val navHostFragment =
            activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navHostFragment.navController
    }

    override fun navigate(to: Route) {
        when (to) {
            is Route.ActionSearchFragmentToHospitalDetailFragment -> {
                val action = SearchFragmentDirections.actionSearchFragmentToHospitalDetailFragment(to.id)
                navController.navigate(action)
            }

            is Route.ActionHospitalDetailFragmentToWebViewActivity -> {
                val action =
                    HospitalDetailFragmentDirections.actionHospitalDetailFragmentToWebViewActivity(to.url)
                navController.navigate(action)
            }
        }
    }

    override fun popBackStack() {
        navController.popBackStack()
    }
}