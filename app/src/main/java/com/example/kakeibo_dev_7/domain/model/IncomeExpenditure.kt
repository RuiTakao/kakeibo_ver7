package com.example.kakeibo_dev_7.domain.model

import com.google.firebase.firestore.DocumentId

data class IncomeExpenditure(
    @DocumentId
    val id: String = "",
    val payDate: String = "",
    val price: String = "",
    val categoryId: String = "",
    val content: String = "",
    val kind: String = "",
    val userId: String = "",
    val created: String = "",
    val updated: String = ""
)
