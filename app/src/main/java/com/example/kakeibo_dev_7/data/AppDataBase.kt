package com.example.kakeibo_dev_7.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kakeibo_dev_7.domain.model.User
import com.example.kakeibo_dev_7.domain.repository.UserDao

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
}