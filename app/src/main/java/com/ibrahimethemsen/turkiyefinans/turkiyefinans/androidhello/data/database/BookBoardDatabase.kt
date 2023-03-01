package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.domain.model.room.BookEntity

@Database(
    entities = [BookEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BookBoardDatabase : RoomDatabase() {
    abstract fun bookDao() : BookDao
}