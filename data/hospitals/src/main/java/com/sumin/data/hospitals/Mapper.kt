package com.sumin.data.hospitals

import com.sumin.data.hospitals.models.HospitalApiModel
import com.sumin.list.hospital.HospitalModel

fun HospitalApiModel.toHospitalModel(): HospitalModel {
    return HospitalModel(
        hospitalName = yadmNm,
        telephoneNumber = telno,
        homepageUrl = hospUrl,
        address = addr
    )
}