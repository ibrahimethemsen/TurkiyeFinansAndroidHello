package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.register

import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): BaseViewModel() {
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

