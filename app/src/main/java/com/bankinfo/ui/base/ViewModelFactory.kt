package com.bankinfo.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bankinfo.ui.main.viewmodel.MainViewModel
import com.bankinfo.networkcall.api.ApiHelper
import com.bankinfo.networkcall.repository.MainRepository

class ViewModelFactory(private val apiHelper: ApiHelper,private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper),application) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

