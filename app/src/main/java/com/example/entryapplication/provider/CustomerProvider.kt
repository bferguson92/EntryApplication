package com.example.entryapplication.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import com.example.entrydisplay.database.CustomerDatabase

class CustomerProvider : ContentProvider() {


    private val authority = "com.example.entryapplication..provider.CustomerProvider"
    private val url = "contents://$authority/customer"

    private val ALL_CUSTOMERS = 1
    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    private var customerDb: CustomerDatabase? = null

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        position: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var cursor: Cursor? = null
        when(uriMatcher.match(uri)){
            ALL_CUSTOMERS ->{
                cursor = customerDb?.customerDAO()?.getAllCustomer()
            }
        }

        return cursor
    }

    override fun onCreate(): Boolean {
        uriMatcher.apply {
            addURI(authority, "customer", ALL_CUSTOMERS)
        }

        context?.let { context ->
            customerDb = Room.databaseBuilder(context, CustomerDatabase::class.java, "customer.db")
                .allowMainThreadQueries().build()
        }

        return (customerDb != null)
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        return 0
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        return 0
    }

    override fun getType(p0: Uri): String? {
        return null
    }

}