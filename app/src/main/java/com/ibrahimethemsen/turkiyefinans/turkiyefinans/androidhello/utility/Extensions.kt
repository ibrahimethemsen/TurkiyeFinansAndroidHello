package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility

import android.content.Context
import androidx.datastore.dataStore
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.model.UserSettingsSerializer


val Context.dataStore by dataStore(
    fileName = "user-settings.json",
    serializer = UserSettingsSerializer(CryptoManager())
)