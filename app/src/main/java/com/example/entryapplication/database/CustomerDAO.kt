package com.example.entrydisplay.database

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CustomerDAO{
    @Query("SELECT * FROM customer")
    fun getAllCustomer(): Cursor

    @Insert
    fun insertNewCustomer(customerEntity: CustomerEntity)
}