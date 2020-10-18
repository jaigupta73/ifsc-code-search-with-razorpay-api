package com.bankinfo.networkcall.models.ifscinfoModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class IfscResponse(
    @ColumnInfo(name = "ADDRESS") val ADDRESS: String,
    @ColumnInfo(name = "BANK") val BANK: String,
    @ColumnInfo(name = "BANKCODE") val BANKCODE: String,
    @ColumnInfo(name = "BRANCH") val BRANCH: String,
    @ColumnInfo(name = "CENTRE") val CENTRE: String,
    @ColumnInfo(name = "CITY") val CITY: String,
    @ColumnInfo(name = "CONTACT") val CONTACT: String,
    @ColumnInfo(name = "DISTRICT") val DISTRICT: String,
    @PrimaryKey var id: Long,
    @ColumnInfo(name = "IFSC") val IFSC: String,
    @ColumnInfo(name = "IMPS") val IMPS: Boolean,
    @ColumnInfo(name = "MICR") val MICR: String,
    @ColumnInfo(name = "NEFT") val NEFT: Boolean,
    @ColumnInfo(name = "RTGS") val RTGS: Boolean,
    @ColumnInfo(name = "STATE") val STATE: String,
    @ColumnInfo(name = "UPI") val UPI: Boolean
): Serializable

