package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility

import android.content.Context
import android.content.Context.MODE_PRIVATE

class SharedManager(context : Context) {
    private val sharedPreferences = context.getSharedPreferences(SHARED_PREF,MODE_PRIVATE)

    fun setSharedPreference(key : String, putValue : Boolean){
        sharedPreferences.edit().putBoolean(key, putValue).apply()
    }
    fun getSharedPreference(key : String,defValue : Boolean):Boolean{
        return sharedPreferences.getBoolean(key, defValue)
    }

    companion object{
        private const val SHARED_PREF = "shared"
    }
}