package com.sumin.data.hospitals.remote

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Parcelize
@Xml(name = "item")
data class HospitalApiModel(

	/** 암호화된 요양기호 */
	@PropertyElement(name = "ykiho")
	val ykiho: String,

	/** 종별 코드 */
	@PropertyElement(name = "clCd")
	val clCd: String,

	/** 종별 코드 명 */
	@PropertyElement(name = "clCdNm")
	val clCdNm: String? = null,

	/** 병원명 */
	@PropertyElement(name = "yadmNm")
	val yadmNm: String? = null,

	/** 전화번호 */
	@PropertyElement(name = "telno")
	val telno: String? = null,

	/** 홈페이지 */
	@PropertyElement(name = "hospUrl")
	val hospUrl: String? = null,

	/** 개설일자 */
	@PropertyElement(name = "estbDd")
	val estbDd: String? = null,

	/** 주소 */
	@PropertyElement(name = "addr")
	val addr: String? = null,

	/** x좌표 */
	@PropertyElement(name = "XPos")
	val xPos: String? = null,

	/** y좌표 */
	@PropertyElement(name = "YPos")
	val yPos: String? = null,

	/** 거리 */
	@PropertyElement(name = "distance")
	val distance: String? = null,

	/** 우편번호 */
	@PropertyElement(name = "postNo")
	val postNo: String? = null,

	/** 시도코드 */
	@PropertyElement(name = "sidoCd")
	val sidoCd: Int? = null,

	/** 시도명 */
	@PropertyElement(name = "sidoCdNm")
	val sidoCdNm: String? = null,

	/** 시군구코드 */
	@PropertyElement(name = "sgguCd")
	val sgguCd: Int? = null,

	/** 시군구명 */
	@PropertyElement(name = "sgguCdNm")
	val sgguCdNm: String? = null,

	/** 읍면동명 */
	@PropertyElement(name = "emdongNm")
	val emdongNm: String? = null,

	/** 의사총수 */
	@PropertyElement(name = "drTotCnt")
	val drTotCnt: String? = null,

	/** 한방전문의 인원수 */
	@PropertyElement(name = "cmdcSdrCnt")
	val cmdcSdrCnt: String? = null,

	/** 한방일반의 인원수 */
	@PropertyElement(name = "cmdcGdrCnt")
	val cmdcGdrCnt: String? = null,

	/** 한방레지던트 인원수 */
	@PropertyElement(name = "cmdcResdntCnt")
	val cmdcResdntCnt: String? = null,

	/** 한방인턴 인원수 */
	@PropertyElement(name = "cmdcIntnCnt")
	val cmdcIntnCnt: String? = null,

	/** 외과전문의 인원수 */
	@PropertyElement(name = "mdeptSdrCnt")
	val mdeptSdrCnt: String? = null,

	/** 외과일반의 인원수 */
	@PropertyElement(name = "mdeptGdrCnt")
	val mdeptGdrCnt: String? = null,

	/** 외과레지던트 인원수 */
	@PropertyElement(name = "mdeptResdntCnt")
	val mdeptResdntCnt: String? = null,

	/** 의과인턴 인원수 */
	@PropertyElement(name = "mdeptIntnCnt")
	val mdeptIntnCnt: String? = null,

	/** 치과전문의 인원수 */
	@PropertyElement(name = "detySdrCnt")
	val detySdrCnt: String? = null,

	/** 치과일반의 인원수 */
	@PropertyElement(name = "detyGdrCnt")
	val detyGdrCnt: String? = null,

	/** 치과레지던트 인원수 */
	@PropertyElement(name = "detyResdntCnt")
	val detyResdntCnt: String? = null,

	/** 치과인턴 인원수 */
	@PropertyElement(name = "detyIntnCnt")
	val detyIntnCnt: String? = null,

	/** 조산사 인원수 */
	@PropertyElement(name = "pnursCnt")
	val pnursCnt: String? = null,

) : Parcelable
