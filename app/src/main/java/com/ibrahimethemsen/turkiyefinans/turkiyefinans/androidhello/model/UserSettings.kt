package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.model

import kotlinx.serialization.Serializable


@Serializable
data class UserSettings(
    val deviceId : String? = null,
    val email : String? = null,
    val password : String? = null,
    val securityPassword : String? = null,
    val nickName : String? = null,
    val gender : String? = null
)
