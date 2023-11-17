package com.sumin.list.hospital

data class HospitalQuery(
    val sidoCd: String? = null,
    val sgguCd: String? = null,
    val emdongNm: String? = null,
    val yadmNm: String = "",
    val zipCd: String? = null,
    val clCd: String? = null,
    val dgsbjtCd: String? = null,
    val xPos: String? = null,
    val yPos: String? = null,
    val radius: String? = null
) {

    companion object {
        private const val NUM_OF_ROWS = 30
    }
}