package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.datastore.dataStore
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.model.UserSettingsSerializer

val Context.dataStore by dataStore(
    fileName = "user.json",
    serializer = UserSettingsSerializer(CryptoManager())
)

fun Context.userInfoMessage(@StringRes stringRes : Int,duration : Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,getString(stringRes),duration).show()
}