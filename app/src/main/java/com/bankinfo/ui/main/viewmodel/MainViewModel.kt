package com.bankinfo.ui.main.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.bankinfo.apiutils.ApiCallabck
import com.bankinfo.database.DataBaseRepository
import com.bankinfo.networkcall.models.ifscinfoModel.IfscResponse
import com.bankinfo.networkcall.repository.MainRepository
import kotlinx.coroutines.Dispatchers


class MainViewModel(private val mainRepository: MainRepository, application: Application) :
    AndroidViewModel(application!!) {
    private val mRepository: DataBaseRepository = DataBaseRepository(application)
    fun getBankData(ifscCode: String) = liveData(Dispatchers.IO) {
        emit(ApiCallabck.loading(data = null))
        try {
            if (mRepository.getBankInfo(ifscCode) != null) {
                emit(ApiCallabck.success(data = mRepository.getBankInfo(ifscCode)))
            } else {
                val ifscResponse = mainRepository.getBankInfoByIFSC(ifscCode)
                ifscResponse.id = System.currentTimeMillis()
                mRepository.insert(ifscResponse)
                emit(ApiCallabck.success(data = ifscResponse))
            }
//
        } catch (exception: Exception) {
            emit(ApiCallabck.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


}