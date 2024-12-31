package com.example.kakeibo_dev_7.presentation.expenditure_list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.kakeibo_dev_7.presentation.component.template.Template

@Composable
fun ExpenditureListScreen(navController: NavController) {
    Template(navController = navController) {
        Text("支出")
    }
}