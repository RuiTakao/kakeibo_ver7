package com.example.kakeibo_dev_7.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakeibo_dev_7.domain.model.User
import com.example.kakeibo_dev_7.domain.repository.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(
    private val userDao: UserDao
): ViewModel() {
    fun createUser() {
        viewModelScope.launch {
            val user = User(userId =  "111111")
            userDao.insertUser(user)
        }
    }
}