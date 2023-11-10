package com.sumin.database.hospital

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hospitals")
data class HospitalEntity(
    /** 암호화된 요양기호 */
    @PrimaryKey
    @ColumnInfo(name = "ykiho")
    val ykiho: String,

    /** 종별 코드 */
    @ColumnInfo(name = "cl_cd")
    val clCd: String,

    /** 종별 코드 명 */
    @ColumnInfo(name = "cl_cd_nm")
    val clCdNm: String? = null,

    /** 병원명 */
    @ColumnInfo(name = "yadm_nm")
    val yadmNm: String? = null,

    /** 전화번호 */
    @ColumnInfo(name = "tel_no")
    val telno: String? = null,

    /** 홈페이지 */
    @ColumnInfo(name = "hosp_url")
    val hospUrl: String? = null,

    /** 개설일자 */
    @ColumnInfo(name = "estb_dd")
    val estbDd: String? = null,

    /** 주소 */
    @ColumnInfo(name = "addr")
    val addr: String? = null,

    /** x좌표 */
    @ColumnInfo(name = "x_pos")
    val xPos: String? = null,

    /** y좌표 */
    @ColumnInfo(name = "y_pos")
    val yPos: String? = null,

    /** 거리 */
    @ColumnInfo(name = "distance")
    val distance: String? = null,

    /** 우편번호 */
    @ColumnInfo(name = "post_no")
    val postNo: String? = null,

    /** 시도코드 */
    @ColumnInfo(name = "sido_cd")
    val sidoCd: Int? = null,

    /** 시도명 */
    @ColumnInfo(name = "sido_cd_nm")
    val sidoCdNm: String? = null,

    /** 시군구코드 */
    @ColumnInfo(name = "sggu_cd")
    val sgguCd: Int? = null,

    /** 시군구명 */
    @ColumnInfo(name = "sggu_cd_nm")
    val sgguCdNm: String? = null,

    /** 읍면동명 */
    @ColumnInfo(name = "emdong_nm")
    val emdongNm: String? = null,

    /** 의사총수 */
    @ColumnInfo(name = "dr_tot_cnt")
    val drTotCnt: String? = null,

    /** 한방전문의 인원수 */
    @ColumnInfo(name = "cmdc_sdr_cnt")
    val cmdcSdrCnt: String? = null,

    /** 한방일반의 인원수 */
    @ColumnInfo(name = "cmdc_gdr_cnt")
    val cmdcGdrCnt: String? = null,

    /** 한방레지던트 인원수 */
    @ColumnInfo(name = "cmdc_resdnt_cnt")
    val cmdcResdntCnt: String? = null,

    /** 한방인턴 인원수 */
    @ColumnInfo(name = "cmdc_intn_cnt")
    val cmdcIntnCnt: String? = null,

    /** 외과전문의 인원수 */
    @ColumnInfo(name = "mdept_sdr_cnt")
    val mdeptSdrCnt: String? = null,

    /** 외과일반의 인원수 */
    @ColumnInfo(name = "mdept_gdr_cnt")
    val mdeptGdrCnt: String? = null,

    /** 외과레지던트 인원수 */
    @ColumnInfo(name = "mdept_resdnt_cnt")
    val mdeptResdntCnt: String? = null,

    /** 의과인턴 인원수 */
    @ColumnInfo(name = "mdept_intn_cnt")
    val mdeptIntnCnt: String? = null,

    /** 치과전문의 인원수 */
    @ColumnInfo(name = "dety_sdr_cnt")
    val detySdrCnt: String? = null,

    /** 치과일반의 인원수 */
    @ColumnInfo(name = "dety_gdr_cnt")
    val detyGdrCnt: String? = null,

    /** 치과레지던트 인원수 */
    @ColumnInfo(name = "dety_resdnt_cnt")
    val detyResdntCnt: String? = null,

    /** 치과인턴 인원수 */
    @ColumnInfo(name = "dety_intn_cnt")
    val detyIntnCnt: String? = null,

    /** 조산사 인원수 */
    @ColumnInfo(name = "pnurs_cnt")
    val pnursCnt: String? = null,
)