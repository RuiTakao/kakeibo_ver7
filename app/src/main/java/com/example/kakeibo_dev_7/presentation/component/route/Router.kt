package com.example.kakeibo_dev_7.presentation.component.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kakeibo_dev_7.presentation.edit_income_expenditure.EditIncomeExpenditureScreen
import com.example.kakeibo_dev_7.presentation.expenditure_detail.ExpenditureDetailScreen
import com.example.kakeibo_dev_7.presentation.expenditure_list.ExpenditureListScreen
import com.example.kakeibo_dev_7.presentation.income_detail.IncomeDetailScreen
import com.example.kakeibo_dev_7.presentation.income_list.IncomeListScreen
import com.example.kakeibo_dev_7.presentation.payment_detail.PaymentDetailScreen
import com.example.kakeibo_dev_7.presentation.setting.SettingScreen

/**
 * ルーティング
 *
 * @param navController NavHostController
 *
 * @return Unit
 */
@Composable
fun Router(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = ScreenRoute.EditIncomeExpenditure.route
    ) {

        /* 収支入力 */
        composable(route = ScreenRoute.EditIncomeExpenditure.route) {
            EditIncomeExpenditureScreen(navController = navController)
        }

        /* 支出 */
        composable(route = ScreenRoute.ExpenditureList.route) {
            ExpenditureListScreen(navController = navController)
        }

        /* 支出詳細 */
        composable(route = ScreenRoute.ExpenditureDetail.route) {
            ExpenditureDetailScreen()
        }

        /* 収入 */
        composable(route = ScreenRoute.IncomeList.route) {
            IncomeListScreen()
        }

        /* 収入詳細 */
        composable(route = ScreenRoute.IncomeDetail.route) {
            IncomeDetailScreen()
        }

        /* 明細 */
        composable(route = ScreenRoute.PaymentDetail.route) {
            PaymentDetailScreen()
        }

        /* 設定 */
        composable(route = ScreenRoute.Setting.route) {
            SettingScreen()
        }
    }
}