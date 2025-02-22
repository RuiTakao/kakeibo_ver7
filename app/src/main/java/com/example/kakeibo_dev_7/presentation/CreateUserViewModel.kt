package com.example.kakeibo_dev_7.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakeibo_dev_7.domain.model.User
import com.example.kakeibo_dev_7.domain.repository.UserDao
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(
    private val userDao: UserDao
): ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    fun createUser() {
        val user = User(userId =  "111111")
        viewModelScope.launch {

            userDao.insertUser(user)
        }

        val data = hashMapOf(
            "name" to "11111"
        )

        val db = FirebaseFirestore.getInstance()

        db.collection("users")
            .add(data)
    }
}