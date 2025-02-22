package com.example.kakeibo_dev_7.domain.model

import com.google.firebase.firestore.DocumentId

data class ExpenditureReport (
    @DocumentId
    val id: String = "",
    val categoryName: String = "",
    val paymentPriceSum: Int = 0,
    val paymentCount: Int = 0
)