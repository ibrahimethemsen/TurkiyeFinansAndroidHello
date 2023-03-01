package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.domain.model.room

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.library.data.Category

@Entity("book_entity")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "book_name") val bookName: String,
    @ColumnInfo(name = "sub_title") val subTitle: String,
    @ColumnInfo(name = "rating") val rating: Int? = 0,
    @ColumnInfo(name = "pages") val pages: Int,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean = false,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "language") val language: String? = "TR",
    @ColumnInfo(name = "cover") val cover: Bitmap,
    @ColumnInfo(name = "category") val category: Category
)
