package com.example.kakeibo_dev_7.domain.model

import com.google.firebase.firestore.DocumentId

data class Category(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val kind: String = "",
    val no_delete: Boolean = false,
    val user_id: String = ""
)
