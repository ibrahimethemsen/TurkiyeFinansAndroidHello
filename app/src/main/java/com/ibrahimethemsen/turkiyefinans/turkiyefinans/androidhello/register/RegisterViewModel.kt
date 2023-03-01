package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.register

import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.base.BaseViewModel

class RegisterViewModel : BaseViewModel() {
    fun isCheckRegister(
        email: String?,
        password: String?,
        securityPassword: String?,
        nickName: String?,
    ): Boolean {
        return isCheckEditText(email) &&
                isCheckEditText(password) &&
                isCheckEditText(securityPassword) &&
                isCheckEditText(nickName)
    }


}

