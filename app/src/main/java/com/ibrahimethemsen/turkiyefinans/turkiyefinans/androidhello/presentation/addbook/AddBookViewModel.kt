package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.addbook

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.data.database.BookDao
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.domain.model.room.BookEntity
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.base.BaseViewModel
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.library.data.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val bookDao : BookDao
) : BaseViewModel() {
    fun addBook(
        bookName : String,
        subTitle : String,
        pages : Int,
        description : String,
        cover : Bitmap,
        category : Category,
        language : String? = "TR",
        isFavorite : Boolean = false,
        rating : Int? = 0
    ){
        viewModelScope.launch(Dispatchers.IO) {
            bookDao.addBook(BookEntity(
                bookName = bookName,
                subTitle = subTitle,
                rating = rating,
                pages = pages,
                isFavorite = isFavorite,
                description = description,
                language = language,
                cover = cover,
                category = category
            ))
        }
    }
}