package com.bankinfo.database

import android.app.Application
import com.bankinfo.networkcall.models.ifscinfoModel.IfscResponse

internal class DataBaseRepository(application: Application?) {
    private var mDaoAccess: DaoAccess? = null

    fun insert(ifscResponse: IfscResponse?) {
        MyDatabase.databaseWriteExecutor.execute {
            mDaoAccess?.insertIfscResponse(ifscResponse)
        }
    }

    fun getBankInfo(ifscCode: String?): IfscResponse? {
        return mDaoAccess?.fetchBankInfoByIfscCode(ifscCode!!.toUpperCase())
    }


    init {
        val db: MyDatabase? = MyDatabase.getDatabase(application!!.applicationContext)
        mDaoAccess = db?.daoAccess()
    }
}