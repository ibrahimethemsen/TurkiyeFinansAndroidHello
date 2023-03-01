package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.login

import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {
    fun isCheckLogin(
        email: String?,
        password: String?
    ): Boolean {
        return isCheckEditText(email) &&
                isCheckEditText(password)
    }
}