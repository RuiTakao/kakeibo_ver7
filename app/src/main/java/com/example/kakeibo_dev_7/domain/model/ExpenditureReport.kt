package com.example.kakeibo_dev_7.domain.model

data class ExpenditureReport (
    val id: Int,
    val categoryName: String,
    val paymentPriceSum: Int,
    val paymentCount: Int
)