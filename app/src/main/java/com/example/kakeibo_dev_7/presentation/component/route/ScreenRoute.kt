package com.example.kakeibo_dev_7.presentation.component.route

sealed class ScreenRoute(val route: String) {

    /* 収支入力 */
    object EditIncomeExpenditure : ScreenRoute("edit_income_expenditure")

    /* 支出リスト */
    object ExpenditureList : ScreenRoute("expenditure_list")

    /* 支出詳細 */
    object ExpenditureDetail : ScreenRoute("expenditure_detail")

    /* 収入リスト */
    object IncomeList : ScreenRoute("income_list")

    /* 収入詳細 */
    object IncomeDetail : ScreenRoute("income_detail")

    /* 明細 */
    object PaymentDetail : ScreenRoute("payment_detail")

    /* 設定 */
    object Setting : ScreenRoute("setting")
}