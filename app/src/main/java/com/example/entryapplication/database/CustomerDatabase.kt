package com.example.entrydisplay.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CustomerEntity::class], version = 1)
abstract class CustomerDatabase: RoomDatabase() {
    abstract fun customerDAO(): CustomerDAO
}