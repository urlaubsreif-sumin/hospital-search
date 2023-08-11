package com.sumin.data.hospitals

import com.google.gson.GsonBuilder
import com.sumin.data.hospitals.models.HospitalListApiModel
import com.sumin.data.hospitals.models.Response
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface HospitalApi {

    @GET("getHospBasisList")
    suspend fun getHospitalList(
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("sidoCd") sidoCode: String? = null,
        @Query("sgguCd") sgguCode: String? = null,
        @Query("emdongNm") emdongNumber: String? = null,
        @Query("yadmNm") hospitalName: String = "",
        @Query("zipCd") zipCode: String? = null,
        @Query("clCd") clCode: String? = null,
        @Query("dgsbjtCd") dgsbjtCode: String? = null,
        @Query("ServiceKey") serviceKey: String = BuildConfig.API_KEY
    ): Response

    @GET("getHospBasisList")
    fun getHospitalListResponse(
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("sidoCd") sidoCode: String? = null,
        @Query("sgguCd") sgguCode: String? = null,
        @Query("emdongNm") emdongNumber: String? = null,
        @Query("yadmNm") hospitalName: String = "",
        @Query("zipCd") zipCode: String? = null,
        @Query("clCd") clCode: String? = null,
        @Query("dgsbjtCd") dgsbjtCode: String? = null,
        @Query("ServiceKey") serviceKey: String = BuildConfig.API_KEY
    ): Call<Response>


    companion object {
        private const val BASE_URL = "http://apis.data.go.kr/B551182/hospInfoServicev2/"
        private val client = OkHttpClient.Builder()
            .build()

        private val parser = TikXml.Builder().exceptionOnUnreadXml(false).build()

        fun create(): HospitalApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(TikXmlConverterFactory.create(parser))
                .build()
                .create(HospitalApi::class.java)
        }
    }
}