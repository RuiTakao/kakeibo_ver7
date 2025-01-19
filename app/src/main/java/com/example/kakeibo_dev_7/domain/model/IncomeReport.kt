package com.example.kakeibo_dev_7.domain.model

data class IncomeReport(
    val id: Int,
    val categoryName: String,
    val paymentPriceSum: Int,
    val paymentCount: Int
)
