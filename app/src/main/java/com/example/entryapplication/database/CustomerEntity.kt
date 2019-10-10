package com.example.entrydisplay.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true) var primaryKey: Int?,
    @ColumnInfo(name = "customer_name") var customerName: String,
    @ColumnInfo(name = "customer_relation") var customerRelation: String
) {
    constructor(
        customerName: String,
        customerRelation: String
    ) : this(null, customerName, customerRelation)
}