package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.data.di

import android.content.Context
import androidx.room.Room
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.data.database.BookBoardDatabase
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.data.database.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RoomModule {
    @[Provides Singleton]
    fun provideDatabase(
        @ApplicationContext context : Context
    ) : BookBoardDatabase{
        return Room.databaseBuilder(
            context,
            BookBoardDatabase::class.java,
            "booksboard_database"
        ).fallbackToDestructiveMigration().build()
    }

    @[Provides Singleton]
    fun provideBookDao(
        db : BookBoardDatabase
    ) : BookDao{
        return db.bookDao()
    }
}