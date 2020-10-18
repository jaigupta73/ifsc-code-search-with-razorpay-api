package com.bankinfo.networkcall.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getBankDetailByIFSCCode(ifscCode: String) = apiService.getBankDetailByIFSC(ifscCode)
}