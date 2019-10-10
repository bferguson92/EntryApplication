package com.example.entryapplication.util

import android.util.Log

object Logger{
    private const val errorTag = "TAG_ERROR"

    fun error(throwable: Throwable){
        Log.e(errorTag, throwable.toString())
    }
}