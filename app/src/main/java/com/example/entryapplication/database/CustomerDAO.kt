package com.example.entrydisplay.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao

interface CustomerDAO{
    @Query("SELECT * FROM customer")
    fun getAllCustomer(): List<CustomerEntity>

    @Insert
    fun insertAllCustomer(vararg customerEntity: CustomerEntity)
}