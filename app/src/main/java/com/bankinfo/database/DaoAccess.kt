package com.bankinfo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bankinfo.networkcall.models.ifscinfoModel.IfscResponse

@Dao
interface DaoAccess {
    @Insert
    fun insertIfscResponse(ifscResponse: IfscResponse?): Long

    @Query("SELECT * FROM " + MyDatabase.TABLE_NAME_TODO)
    fun fetchAllBankInfo(): LiveData<List<IfscResponse?>>?

    @Query("SELECT * FROM " + MyDatabase.TABLE_NAME_TODO + " WHERE IFSC = :ifscCode LIMIT 1")
    fun fetchBankInfoByIfscCode(ifscCode: String): IfscResponse?

}