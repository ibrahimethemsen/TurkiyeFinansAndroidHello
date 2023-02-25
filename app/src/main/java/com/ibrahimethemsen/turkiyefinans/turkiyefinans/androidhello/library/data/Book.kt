package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.library.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class Category{
    HISTORY,
    SELF_IMPROVEMENT,
    SOCIOLOGY
}

data class Book(
    val id : Int,
    val name : String,
    @StringRes val subtitle : Int,
    val rating : Int,
    val pages : Int,
    val isFavorite : Boolean = false,
    @StringRes val description : Int,
    val language : String,
    @DrawableRes val cover : Int,
    val category : Category
)
