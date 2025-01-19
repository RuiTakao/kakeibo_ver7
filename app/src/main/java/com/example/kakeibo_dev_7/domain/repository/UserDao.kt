package com.example.kakeibo_dev_7.domain.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kakeibo_dev_7.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User")
    fun getUser(): Flow<List<User>>
}