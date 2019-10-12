package com.example.entrydisplay.database

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CustomerDAO{
    @Query("SELECT * FROM customer")
    fun getAllCustomer(): Cursor

    @Query("DELETE FROM customer")
    fun deleteAllCustomers()

    @Insert
    fun insertNewCustomer(customerEntity: CustomerEntity)
}