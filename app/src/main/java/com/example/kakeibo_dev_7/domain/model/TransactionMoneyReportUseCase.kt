package com.example.kakeibo_dev_7.domain.model

import com.example.kakeibo_dev_7.common.IncomeExpenditureStatus

class TransactionMoneyReportUseCase {
    fun use_case(): List<TransactionMoneyReport> {
        val mock1 = TransactionMoney(
            id = 1,
            content = "夜食",
            amount = 1000,
            transactionMoneyDate = "2024/12/21",
            transactionKind = IncomeExpenditureStatus.Expenditure,
            categoryId = 1,
            categoryName = "食費"
        )
        val mock2 = TransactionMoney(
            id = 2,
            content = "昼ご飯",
            amount = 1500,
            transactionMoneyDate = "2024/12/21",
            transactionKind = IncomeExpenditureStatus.Expenditure,
            categoryId = 1,
            categoryName = "食費"
        )
        val mock3 = TransactionMoney(
            id = 3,
            content = "12月給与",
            amount = 200000,
            transactionMoneyDate = "2024/12/21",
            transactionKind = IncomeExpenditureStatus.Income,
            categoryId = 1,
            categoryName = "給与"
        )

        val mockList1 = TransactionMoneyReport(
            id = 1,
            transactionMoneyDate = "2024/12/21",
            transactionMoneyList = listOf(mock1, mock2, mock3)
        )

        val mock4 = TransactionMoney(
            id = 1,
            content = "夜食",
            amount = 900,
            transactionMoneyDate = "2024/12/24",
            transactionKind = IncomeExpenditureStatus.Expenditure,
            categoryId = 1,
            categoryName = "食費"
        )
        val mock5 = TransactionMoney(
            id = 2,
            content = "昼ご飯",
            amount = 1100,
            transactionMoneyDate = "2024/12/24",
            transactionKind = IncomeExpenditureStatus.Expenditure,
            categoryId = 1,
            categoryName = "食費"
        )

        val mockList2 = TransactionMoneyReport(
            id = 2,
            transactionMoneyDate = "2024/12/24",
            transactionMoneyList = listOf(mock4, mock5)
        )

        return listOf(mockList1, mockList2)
    }
}