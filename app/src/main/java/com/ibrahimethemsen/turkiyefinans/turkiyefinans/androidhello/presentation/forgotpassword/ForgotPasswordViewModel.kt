package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.forgotpassword

import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(): BaseViewModel() {
    fun isCheckForgotPassword(
        email: String?,
        password: String?
    ): Boolean {
        return isCheckEditText(email) &&
                isCheckEditText(password)
    }
}