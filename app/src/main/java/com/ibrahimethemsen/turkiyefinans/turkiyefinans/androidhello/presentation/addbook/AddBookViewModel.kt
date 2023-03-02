package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.addbook

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.data.database.BookDao
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.domain.model.room.BookEntity
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.base.BaseViewModel
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.component.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val bookDao: BookDao
) : BaseViewModel() {
    fun addBook(
        bookName: String,
        subTitle: String,
        pages: Int,
        description: String,
        cover: Bitmap,
        category: Category,
        language: String? = "TR",
        rating: Int? = 0,
        isFavorite: Boolean = false,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            bookDao.addBook(
                BookEntity(
                    bookName = bookName,
                    subTitle = subTitle,
                    rating = rating,
                    pages = pages,
                    isFavorite = isFavorite,
                    description = description,
                    language = language,
                    cover = cover,
                    category = category
                )
            )
        }
    }

    fun isCheckInputBook(
        bookName: String?,
        subTitle: String?,
        pages: Int?,
        description: String?,
        language: String? = "TR",
        rating: Int? = 0
    )  : Boolean{
        return isCheckEditText(bookName,0) &&
            isCheckEditText(subTitle,0) &&
            pages != null &&
            isCheckEditText(description,0) &&
            isCheckEditText(language,0) &&
            rating != null
    }
}