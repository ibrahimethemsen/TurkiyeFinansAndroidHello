package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.login

import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.base.BaseViewModel

class LoginViewModel : BaseViewModel() {
    fun isCheckLogin(
        email: String?,
        password: String?
    ): Boolean {
        return isCheckEditText(email) &&
                isCheckEditText(password)
    }
}