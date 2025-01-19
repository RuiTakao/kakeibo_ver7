package com.example.kakeibo_dev_7.di

import android.content.Context
import androidx.room.Room
import com.example.kakeibo_dev_7.data.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDataBase::class.java, "main.db").build()

    @Provides
    fun provideUserDao(db: AppDataBase) = db.userDao()
}