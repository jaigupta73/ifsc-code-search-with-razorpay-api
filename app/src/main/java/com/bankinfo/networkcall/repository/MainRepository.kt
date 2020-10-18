package com.bankinfo.networkcall.repository

import com.bankinfo.networkcall.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getBankInfoByIFSC(ifscCode: String) = apiHelper.getBankDetailByIFSCCode(ifscCode)
}