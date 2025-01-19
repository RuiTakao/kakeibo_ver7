package com.example.kakeibo_dev_7.domain.model

import com.example.kakeibo_dev_7.common.IncomeExpenditureStatus

data class TransactionMoneyReport(
    val id: Int,
    val transactionMoneyDate: String,
    val transactionMoneyList: List<TransactionMoney>
)
