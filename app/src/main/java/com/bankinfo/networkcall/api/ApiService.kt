package com.bankinfo.networkcall.api

import com.bankinfo.networkcall.models.ifscinfoModel.IfscResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/{ifscCode}")
    suspend fun getBankDetailByIFSC(@Path(value = "ifscCode") ifscCode: String): IfscResponse

}