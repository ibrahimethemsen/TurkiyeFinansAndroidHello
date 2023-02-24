package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.model

import kotlinx.serialization.Serializable

const val empty = "empty"
@Serializable
data class UserSettings(
    val deviceId : String? = empty,
    val email : String? = empty,
    val password : String? = empty,
    val securityPassword : String? = empty,
    val nickName : String? = empty,
    val gender : String? = empty
)
