package com.bankinfo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bankinfo.networkcall.models.ifscinfoModel.IfscResponse
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = [IfscResponse::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun daoAccess(): DaoAccess?

    companion object {
        const val DB_NAME = "bank_db"
        const val TABLE_NAME_TODO = "ifscresponse"


        @Volatile
        private var INSTANCE: MyDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        open fun getDatabase(context: Context): MyDatabase? {
            if (INSTANCE == null) {
                synchronized(MyDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder<MyDatabase>(
                            context.applicationContext,
                            MyDatabase::class.java, DB_NAME
                        )
                            .addCallback(sRoomDatabaseCallback)
                            .build()
                    }
                }
            }
            return INSTANCE
        }
        private val sRoomDatabaseCallback: Callback = object : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                databaseWriteExecutor.execute {

                    // Populate the database in the background.
                    // If you want to start with more words, just add them.
                    val dao: DaoAccess? = INSTANCE?.daoAccess()

                }
            }
        }
    }
}