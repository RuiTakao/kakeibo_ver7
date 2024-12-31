package com.example.kakeibo_dev_7.presentation.component.bottom_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kakeibo_dev_7.presentation.component.route.ScreenRoute

@Composable
fun BottomBar(navController: NavController) {

    BottomAppBar(
        modifier = Modifier
            .padding(0.dp)
            .height(70.dp)
    ) {

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(ScreenRoute.EditIncomeExpenditure.route)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "編集",
                    modifier = Modifier.background(Color.Transparent)
                )
            },
            enabled = false
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(ScreenRoute.ExpenditureList.route)
            },
            icon = { Text(text = "支出") }
        )

        NavigationBarItem(
            selected = true,
            onClick = {
                navController.navigate(ScreenRoute.IncomeList.route)
            },
            icon = { Text(text = "収入") }
        )

        NavigationBarItem(
            selected = true,
            onClick = {
                navController.navigate(ScreenRoute.PaymentDetail.route)
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.ReceiptLong,
                    contentDescription = "明細"
                )
            }
        )

        NavigationBarItem(
            selected = true,
            onClick = {
                navController.navigate(ScreenRoute.Setting.route)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.MoreHoriz,
                    contentDescription = "設定"
                )
            }
        )
    }
}