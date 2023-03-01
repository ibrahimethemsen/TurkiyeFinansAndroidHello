package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.domain.model.room.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert
    suspend fun addBook(book : BookEntity)

    @Query("SELECT * FROM book_entity ")
    fun getBook() : Flow<List<BookEntity>>
}