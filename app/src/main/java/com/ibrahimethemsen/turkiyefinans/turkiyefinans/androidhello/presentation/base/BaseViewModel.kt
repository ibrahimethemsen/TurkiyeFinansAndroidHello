package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.base

import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    protected fun isCheckEditText(stringValue: String?): Boolean {
        return !stringValue.isNullOrBlank() && stringValue.isNotEmpty() && stringValue.length > 6
    }
}