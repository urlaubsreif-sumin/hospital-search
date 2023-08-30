package com.sumin.hospital

import android.util.Log
import androidx.navigation.NavController
import com.sumin.feature.search.SearchFragmentDirections
import com.sumin.hospital_detail.HospitalDetailFragmentDirections
import com.sumin.navigation.Navigator
import com.sumin.navigation.Route

class NavControllerHolder : Navigator {
    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun removeNavController() {
        navController = null
    }

    override fun navigate(to: Route) {
        Log.i("[navigate test]", "navigate to -> ${to.toString()}")
        when (to) {
            is Route.ActionSearchFragmentToHospitalDetailFragment -> {
                navigateToHospitalDetailFragment(to.id)
            }

            is Route.ActionHospitalDetailFragmentToWebViewActivity -> {
                Log.i("[navigate test]", "url = ${to.url}")
                navigateToHospitalHomepage(to.url)
            }
        }
    }

    private fun navigateToHospitalDetailFragment(id: String) {
        val action = SearchFragmentDirections.actionSearchFragmentToHospitalDetailFragment(id)
        navController?.navigate(action)
    }

    private fun navigateToHospitalHomepage(url: String?) {
        val action =
            HospitalDetailFragmentDirections.actionHospitalDetailFragmentToWebViewActivity(url)
        navController?.navigate(action)
    }
}