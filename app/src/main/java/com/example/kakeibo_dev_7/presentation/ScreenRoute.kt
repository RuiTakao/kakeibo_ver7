package com.example.kakeibo_dev_7.presentation

 sealed class ScreenRoute(val route: String) {

     data object AddIncomeExpenditureScreen : ScreenRoute("add_income_expenditure_screen")

     data object ExpenditureReportScreen : ScreenRoute("expenditure_report_screen")
}