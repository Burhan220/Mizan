package com.mizan.app.di

import android.content.Context
import androidx.room.Room
import com.mizan.app.data.local.AppDatabase
import com.mizan.app.repository.AppRepository

object AppModule {
    @Volatile
    private var database: AppDatabase? = null

    @Volatile
    private var repository: AppRepository? = null

    fun provideDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            database ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "mizan.db"
            )
                .fallbackToDestructiveMigration()
                .build()
                .also { database = it }
        }
    }

    fun provideRepository(context: Context): AppRepository {
        return repository ?: synchronized(this) {
            repository ?: AppRepository(provideDatabase(context)).also { repository = it }
        }
    }
}
