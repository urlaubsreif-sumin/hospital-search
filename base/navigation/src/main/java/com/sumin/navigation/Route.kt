package com.sumin.navigation


sealed class Route {
    data class ActionSearchFragmentToHospitalDetailFragment(val id: String): Route()
    data class ActionSearchFragmentToFavoriteBottomSheetDialog(val id: String): Route()
    data class ActionHospitalDetailFragmentToWebViewActivity(val url: String?): Route()
}