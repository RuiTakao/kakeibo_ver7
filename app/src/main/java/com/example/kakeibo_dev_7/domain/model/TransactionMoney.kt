package com.example.kakeibo_dev_7.domain.model

import com.example.kakeibo_dev_7.common.IncomeExpenditureStatus

data class TransactionMoney(
    val id: Int,
    val content: String,
    val amount: Int,
    val transactionMoneyDate: String,
    val transactionKind: IncomeExpenditureStatus,
    val categoryId: Int,
    val categoryName: String
)
