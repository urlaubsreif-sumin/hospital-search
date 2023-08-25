package com.sumin.data.hospitals

import com.sumin.data.hospitals.remote.HospitalApiModel
import com.sumin.database.HospitalEntity
import com.sumin.list.hospital.HospitalDetailModel
import com.sumin.list.hospital.HospitalModel

fun HospitalApiModel.toHospitalModel(): HospitalModel {
    return HospitalModel(
        id = ykiho,
        code = clCd,
        hospitalName = yadmNm,
        sidoAddr = sidoCdNm,
        sgguAddr = sgguCdNm
    )
}

fun HospitalApiModel.toHospitalDetailModel(): HospitalDetailModel {
    return HospitalDetailModel(
        id = ykiho,
        code = clCd,
        hospitalName = yadmNm,
        sidoAddr = sidoCdNm,
        sgguAddr = sgguCdNm,
        telNo = telno,
        hompageUrl = hospUrl,
        estbDate = estbDd,
        addr = addr
    )
}

fun HospitalEntity.toHospitalModel(): HospitalModel {
    return HospitalModel(
        id = ykiho,
        code = clCd,
        hospitalName = yadmNm,
        sidoAddr = sidoCdNm,
        sgguAddr = sgguCdNm
    )
}

fun HospitalEntity.toHospitalDetailModel(): HospitalDetailModel {
    return HospitalDetailModel(
        id = ykiho,
        code = clCd,
        hospitalName = yadmNm,
        sidoAddr = sidoCdNm,
        sgguAddr = sgguCdNm,
        telNo = telno,
        hompageUrl = hospUrl,
        estbDate = estbDd,
        addr = addr
    )
}

fun HospitalDetailModel.toHospitalEntity(): HospitalEntity {
    return HospitalEntity(
        ykiho = id,
        clCd = code,
        yadmNm = hospitalName,
        sidoCdNm = sidoAddr,
        sgguCdNm = sgguAddr,
        telno = telNo,
        hospUrl = hompageUrl,
        estbDd = estbDate,
        addr = addr
    )
}