package com.example.kakeibo_dev_7.presentation.edit_income_expenditure

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.kakeibo_dev_7.presentation.component.template.Template

@Composable
fun EditIncomeExpenditureScreen(navController: NavController) {
    Template(navController = navController) {
        Text("収支入力")
    }
}