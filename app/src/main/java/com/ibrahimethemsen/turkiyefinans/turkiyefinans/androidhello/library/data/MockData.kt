package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility

import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.R
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.library.data.Book
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.library.data.Category

val booksList = mutableListOf<Book>(
    Book(
        0,
        "SiyasetName",
        R.string.sub_title,
        4,
        294,
        false,
        R.string.book,
        "TR",
        R.drawable.book,
        Category.HISTORY
    ),
    Book(
        1,
        "Türklerin Tarihi",
        R.string.sub_title_1,
        5,
        320,
        false,
        R.string.book_1,
        "TR",
        R.drawable.book_1,
        Category.HISTORY
    ),
    Book(
        2,
        "Bir Ömür Nasıl Yaşanır",
        R.string.sub_title_2,
        4,
        288,
        false,
        R.string.book_2,
        "TR",
        R.drawable.book_2,
        Category.SELF_IMPROVEMENT
    ),
    Book(
        3,
    "59 Saniye",
        R.string.sub_title_3,
        5,
        344,
        false,
        R.string.book_3,
        "TR",
        R.drawable.book_3,
        Category.SOCIOLOGY
    ),
    Book(
        4,
        "Ted Gibi Konuş",
        R.string.sub_title_4,
        4,
        280,
        false,
        R.string.book_4,
        "TR",
        R.drawable.book_4,
        Category.SELF_IMPROVEMENT
    ),
    Book(
        5,
        "Outliers",
        R.string.sub_title_5,
        5,
        244,
        false,
        R.string.book_5,
        "TR",
        R.drawable.book_5,
        Category.SELF_IMPROVEMENT
    )
)
val favoriteList = mutableListOf<Book>()