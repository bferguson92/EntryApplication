package com.example.entryapplication.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import com.example.entrydisplay.database.CustomerDAO
import com.example.entrydisplay.database.CustomerDatabase

class CustomerProvider:  ContentProvider() {
    private val authority = "com.example.entryapplication.provider.CustomerProvider"

    val ALL_CUSTOMERS = 1

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    private var customerDB: CustomerDatabase? = null


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var cursor: Cursor?

        when(uriMatcher.match(uri)){
            ALL_CUSTOMERS-> cursor = customerDB?.customerDAO()?.getAllCustomer()
            else -> cursor = null
        }

        return cursor
    }

    override fun onCreate(): Boolean {
        uriMatcher.apply {
            addURI(authority, "customer", ALL_CUSTOMERS)
        }

        context?.let { context->
            customerDB = Room.databaseBuilder(context, CustomerDatabase::class.java, "customer.db").allowMainThreadQueries().build()
        }

        return (customerDB != null)
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}