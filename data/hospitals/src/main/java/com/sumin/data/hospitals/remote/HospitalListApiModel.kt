package com.sumin.data.hospitals.remote

import android.os.Parcelable
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import kotlinx.parcelize.Parcelize


@Parcelize
@Xml(name = "response")
data class Response(
    @Element(name = "header")
    val header: Header?,
    @Element(name = "body")
    val body: Body?
) : Parcelable

@Parcelize
@Xml(name = "header")
data class Header(
    @PropertyElement(name = "resultCode")
    val resultCode: Int,

    @PropertyElement(name = "resultMsg")
    val resultMsg: String
) : Parcelable

@Parcelize
@Xml(name = "body")
data class Body(
    @Element(name = "items")
    val hospitalListApiModel: HospitalListApiModel,

    @PropertyElement(name = "numOfRows")
    val numOfRows: Int,

    @PropertyElement(name = "pageNo")
    val pageNo: Int,

    @PropertyElement(name = "totalCount")
    val totalCount: Int
) : Parcelable


@Parcelize
@Xml(name = "items")
data class HospitalListApiModel(
    @Element(name = "item")
    val item: List<HospitalApiModel>

) : Parcelable