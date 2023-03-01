package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.forgotpassword

import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.base.BaseViewModel

class ForgotPasswordViewModel : BaseViewModel() {
    fun isCheckForgotPassword(
        email: String?,
        password: String?
    ): Boolean {
        return isCheckEditText(email) &&
                isCheckEditText(password)
    }
}